package com.inventory.component.main_dashboard;

import com.inventory.component.CustomTextField;
import com.inventory.component.dashboard.ShadowCard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        this.productsTableModel = productsTableModel;
        this.transactionsTableModel = transactionsTableModel;

        initComponents();
        addComponents();
        displayData();

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
                search();
            }
        });
    }

    private void initComponents() {
        cardTitle = new JLabel("Stock History");

        tableDropdown = new JComboBox<>(options);
        tableDropdown.setFont(new Font("Arial", Font.BOLD, 14));
        selectedTable = new JLabel(options[0]);

        searchLabel = new JLabel("Search: ");
        searchTextField = new CustomTextField(new Color(0xd9d9d9), "Enter search?");
        searchTextField.setPreferredSize(new Dimension(100, getHeight()));

    }

    private void addComponents() {
        this.add(cardTitle, "split 2");
        this.add(tableDropdown);
        this.add(searchLabel, "split 2, align right");
        this.add(searchTextField, "wrap, align right");
    }

    private void displayData() {
        productsTable = new JTable(productsTableModel);
        transactionsTable = new JTable(transactionsTableModel);

        productsScrollPane = new JScrollPane(productsTable);
        transactionsScrollPane = new JScrollPane(transactionsTable);

        setupTable(productsTable, productsScrollPane);
        setupTable(transactionsTable, transactionsScrollPane);

        productsScrollPane.setVisible(true);
        transactionsScrollPane.setVisible(false);
    }

    private void setupTable(JTable table, JScrollPane scrollPane) {
        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setGridColor(Color.LIGHT_GRAY);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0xd9d9d9)));
        this.add(scrollPane, "cell 0 2, span 2, grow");
    }

    private void onSelectionPerform(String selected) {
        selectedTable.setText(selected);
        if (selected.equals("Products")) {
            transactionsScrollPane.setVisible(false);
            productsScrollPane.setVisible(true);
        } else if (selected.equals("Transactions")) {
            productsScrollPane.setVisible(false);
            transactionsScrollPane.setVisible(true);
        }
        this.revalidate();
        this.repaint();
    }

    private void search() {
        System.out.println("Searched " + searchTextField.getText());
    }

    public void refreshTableData() {
        displayData();
    }
}
