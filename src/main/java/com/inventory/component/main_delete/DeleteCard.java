package com.inventory.component.main_delete;

import com.inventory.component.CustomButton;
import com.inventory.component.CustomTextField;
import com.inventory.component.dashboard.ShadowCard;
import com.inventory.queries.Product;
import com.inventory.queries.ProductData;
import com.inventory.queries.Transaction;
import com.inventory.queries.TransactionData;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class DeleteCard extends ShadowCard {

    private ProductData pData = new ProductData();
    private TransactionData tData = new TransactionData();

    private JLabel whatToDelete;
    private String[] options = {"Data", "Transaction"};
    private JComboBox<String> dropdown;

    private JLabel productIDLabel;
    private String[] productIDOptions = {};
    private JComboBox<String> productIDCombo;

    private JLabel transactionIDLabel;
    private String[] transactionIDOptions = {};
    private JComboBox<String> transactionIDCombo;

    private CustomButton deleteButton;

    public DeleteCard() {
        initComponents();
        addComponents();

        dropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) dropdown.getSelectedItem();
                onDropdownSelected(selected);
            }
        });

        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonClick();
            }
        });
    }

    private void initComponents() {
        this.setLayout(new MigLayout("insets 25, hidemode 3"));
        whatToDelete = new JLabel("What to delete?");
        whatToDelete.setFont(new Font("Arial", Font.BOLD, 18));
        dropdown = new JComboBox<>(options);
        dropdown.setFont(new Font("Arial", Font.BOLD, 14));

        productIDLabel = new JLabel("Product ID");
        productIDLabel.setFont(new Font("Arial", Font.BOLD, 18));
        productIDCombo = new JComboBox<>(productIDOptions);

        transactionIDLabel = new JLabel("Transaction ID");
        transactionIDLabel.setFont(new Font("Arial", Font.BOLD, 18));
        transactionIDCombo = new JComboBox<>(transactionIDOptions);

        deleteButton = new CustomButton("Delete");
    }

    private void addComponents() {
        this.add(whatToDelete, "wrap");
        this.add(dropdown, "wrap");

        // Section 2
        this.add(productIDLabel, "cell 0 2, wrap");
        this.add(productIDCombo, "cell 0 3, width 100%, wrap");
        this.add(transactionIDLabel, "cell 0 2, wrap");
        this.add(transactionIDCombo, "cell 0 3, width 100%, wrap");

        this.add(deleteButton, "align right");

        productIDLabel.setVisible(true);
        productIDCombo.setVisible(true);
        transactionIDLabel.setVisible(false);
        transactionIDCombo.setVisible(false);
    }

    private void onDropdownSelected(String selected) {
        switchVisibility();
    }

    private void onButtonClick() {
        boolean deleteSuccessful;

        if ((productIDCombo.getSelectedItem() == null && productIDCombo.isVisible()) || (transactionIDCombo.getSelectedItem() == null && transactionIDCombo.isVisible())) {
            JOptionPane.showMessageDialog(null, "Please choose an ID to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (dropdown.getSelectedItem().toString().equals("Data")) {
            Product product = pData.getProductByID(Integer.parseInt(productIDCombo.getSelectedItem().toString()));

            tData.deleteAllTransactionsByProductID(product.getProductID());
            deleteSuccessful = pData.deleteProduct(product.getProductID());
        } else {
            Transaction transaction = tData.getTransactionByID(Integer.parseInt(transactionIDCombo.getSelectedItem().toString()));
            Product product = pData.getProductByID(transaction.getProductID());

            int quantityDeleted = transaction.getQuantity();
            String transactiontype = transaction.getTransactionType();
            boolean isIncoming = transactiontype.equals("Incoming");

            if (product.getStock() < quantityDeleted && isIncoming) {
                JOptionPane.showMessageDialog(null, "Stock not enough to delete incoming transaction of this size!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            pData.updateStock(transaction.getProductID(), quantityDeleted, !isIncoming);

            deleteSuccessful = tData.deleteTransaction(transaction.getTransactionID());
        }

        if (deleteSuccessful) {
            JOptionPane.showMessageDialog(null, "Deletion Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(null, "Deletion Failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        if (productIDCombo.isVisible() && productIDCombo.getSelectedItem() != null) {
            productIDCombo.removeItem(productIDCombo.getSelectedItem());

            if (productIDCombo.getItemCount() > 0) {
                productIDCombo.setSelectedIndex(0);
            }
        }

        if (transactionIDCombo.isVisible() && transactionIDCombo.getSelectedItem() != null) {
            transactionIDCombo.removeItem(transactionIDCombo.getSelectedItem());

            if (transactionIDCombo.getItemCount() > 0) {
                transactionIDCombo.setSelectedIndex(0);
            }
        }
    }

    private void switchVisibility() {
        productIDLabel.setVisible(!productIDLabel.isVisible());
        productIDCombo.setVisible(!productIDCombo.isVisible());
        transactionIDLabel.setVisible(!transactionIDLabel.isVisible());
        transactionIDCombo.setVisible(!transactionIDCombo.isVisible());
    }

    public void setProductIDOptions(String[] options) {
        this.productIDOptions = options;
        this.productIDCombo.setModel(new DefaultComboBoxModel<>(productIDOptions));
    }

    public void setTransactionIDOptions(String[] options) {
        this.transactionIDOptions = options;
        this.transactionIDCombo.setModel(new DefaultComboBoxModel<>(transactionIDOptions));
    }
}
