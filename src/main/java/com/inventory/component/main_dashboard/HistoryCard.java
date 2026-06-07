package com.inventory.component.main_dashboard;

import com.inventory.component.CustomTextField;
import com.inventory.component.dashboard.ShadowCard;
import com.inventory.main.DatabaseConnection;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryCard extends ShadowCard {

    private JLabel cardTitle;

    private String[] options = {"Products", "Transactions"};
    private JComboBox<String> tableDropdown;
    private JLabel selectedTable;

    private JLabel searchLabel;
    private CustomTextField searchTextField;

    private JTable productsTable;
    private DefaultTableModel productsTableModel;

    private JTable  transactionsTable;
    private DefaultTableModel transactionsTableModel;

    private JScrollPane productsScrollPane;
    private JScrollPane transactionsScrollPane;

    public HistoryCard() {
        this.setLayout(new MigLayout("fill, insets 20, hidemode 3"));

        initComponents();
        initTables();
        addComponents();

        displayData("Products", searchTextField.getText());

        tableDropdown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String selected =  (String) tableDropdown.getSelectedItem();
                onSelectionPerform(selected);
            }
        });

        searchTextField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                readAndSearchTransactions(searchTextField.getText());
            }
        });
    }

    private void initComponents() {
        cardTitle = new JLabel("Stock History");

        tableDropdown = new JComboBox<>(options);
        tableDropdown.setFont(new Font("Arial", Font.BOLD, 14));
        selectedTable = new JLabel(options[0]);

        searchLabel = new JLabel("Search: ");
        searchTextField = new CustomTextField(new Color(0xd9d9d9), "Enter product name...");
        searchTextField.setPreferredSize(new Dimension(50, getHeight()));
    }

    private void initTables() {
        String[] transactionTableColumns = {"Transaction ID", "Product Name", "Quantity", "Transaction Type", "Transaction Date"};

        productsTableModel = new  DefaultTableModel();
        transactionsTableModel = new  DefaultTableModel(null, transactionTableColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        productsTable = new JTable(productsTableModel);
        transactionsTable = new JTable(transactionsTableModel);

        productsScrollPane = new JScrollPane(productsTable);
        transactionsScrollPane = new JScrollPane(transactionsTable);

        setupTable(productsTable, productsScrollPane);
        setupTable(transactionsTable, transactionsScrollPane);

        productsScrollPane.setVisible(true);
        transactionsScrollPane.setVisible(false);
    }

    private void displayData(String dataToDisplay, String keyword) {
        if (dataToDisplay.equals("Products")) {
            // display products data in products table
        } else if (dataToDisplay.equals("Transactions")) {
            readAndSearchTransactions(keyword);
        }
    }

    private void addComponents() {
        this.add(cardTitle, "split 2");
        this.add(tableDropdown);
        this.add(searchLabel, "split 2, align right");
        this.add(searchTextField, "wrap, align right, grow");
        this.add(productsScrollPane, "cell 0 2, span 2, grow");
        this.add(transactionsScrollPane, "cell 0 2, span 2, grow");
    }

    private void setupTable(JTable table, JScrollPane scrollPane) {
        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setGridColor(Color.LIGHT_GRAY);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0xd9d9d9)));
    }

    private void onSelectionPerform(String selected) {
        selectedTable.setText(selected);
        if (selected.equals("Products")) {
            transactionsScrollPane.setVisible(false);
            productsScrollPane.setVisible(true);
            displayData("Products", searchTextField.getText());
        } else if (selected.equals("Transactions")) {
            productsScrollPane.setVisible(false);
            transactionsScrollPane.setVisible(true);
            displayData("Transactions", searchTextField.getText());
        }
        this.revalidate();
        this.repaint();
    }

    private void readAndSearchTransactions(String keyword) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        transactionsTableModel.setRowCount(0);

        String query = "SELECT t.transaction_id, p.product_name, t.quantity, t.transaction_type, t.transaction_date " +
                "FROM transaction t " +
                "INNER JOIN product p ON t.productID = p.productID " +
                "WHERE p.product_name LIKE ?";

        try {
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, "%" + keyword + "%");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("transaction_id");
                String name = rs.getString("product_name");
                int qty = rs.getInt("quantity");
                String type = rs.getString("transaction_type");
                String date = rs.getString("transaction_date");

                transactionsTableModel.addRow(new Object[]{id, name, qty, type, date});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void refreshTableData() {
        readAndSearchTransactions(searchTextField.getText());
    }

}
