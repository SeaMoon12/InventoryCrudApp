package com.inventory.component.main_dashboard;

import com.inventory.component.CustomTextField;
import com.inventory.component.dashboard.ShadowCard;
import com.inventory.main.DatabaseConnection;
import com.inventory.queries.ProductData;
import com.inventory.queries.TransactionData;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryCard extends ShadowCard {

    private ProductData pData = new ProductData();
    private TransactionData tData = new TransactionData();

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
        this.setLayout(new MigLayout("fill, insets 20, hidemode 3", "", "[][grow]"));

        initComponents();
        initTables();
        addComponents();

        displayData("Products");

        tableDropdown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String selected =  (String) tableDropdown.getSelectedItem();
                onSelectionPerform(selected);
            }
        });

        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleLiveTextChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleLiveTextChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleLiveTextChange();
            }

            private void handleLiveTextChange() {
                pData.readAndSearchProducts(searchTextField.getText(), productsTableModel);
                tData.readAndSearchTransactions(searchTextField.getText(), transactionsTableModel);
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
        String[] productTableColumns = {"Product ID", "Product Name", "Category", "Stock"};
        String[] transactionTableColumns = {"Transaction ID", "Product Name", "Quantity", "Transaction Type", "Transaction Date"};

        productsTableModel = new DefaultTableModel(null, productTableColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        transactionsTableModel = new DefaultTableModel(null, transactionTableColumns) {
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

    private void displayData(String dataToDisplay) {
        if (dataToDisplay.equals("Products")) {
            pData.readAndSearchProducts(searchTextField.getText(), productsTableModel);
        } else if (dataToDisplay.equals("Transactions")) {
            tData.readAndSearchTransactions(searchTextField.getText(), transactionsTableModel);
        }
    }

    private void addComponents() {
        this.add(cardTitle, "split 2");
        this.add(tableDropdown);
        this.add(searchLabel, "split 2, align right");
        this.add(searchTextField, "wrap, align right, grow");
        this.add(productsScrollPane, "cell 0 1, span 2, grow");
        this.add(transactionsScrollPane, "cell 0 1, span 2, grow");
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
            displayData("Products");
        } else if (selected.equals("Transactions")) {
            productsScrollPane.setVisible(false);
            transactionsScrollPane.setVisible(true);
            displayData("Transactions");
        }
        this.revalidate();
        this.repaint();
    }

    public void refreshTableData() {
        pData.readAndSearchProducts(searchTextField.getText(), productsTableModel);
        tData.readAndSearchTransactions(searchTextField.getText(), transactionsTableModel);
    }

}
