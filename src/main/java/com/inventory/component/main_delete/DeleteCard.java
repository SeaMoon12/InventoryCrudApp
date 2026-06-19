package com.inventory.component.main_delete;

import com.inventory.component.CustomButton;
import com.inventory.component.CustomTextField;
import com.inventory.component.dashboard.ShadowCard;
import com.inventory.queries.*;
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

    private String role;
    private UserData uData = new UserData();

    private JLabel userIDLabel;
    private String[] userIDOptions = {};
    private JComboBox<String> userIDCombo;

    private CustomButton deleteButton;

    public DeleteCard(String role) {
        this.role = role;

        if (role.equals("Admin")) {
            this.options = new String[]{"Data", "Transaction", "User"};
        }

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

        userIDLabel = new JLabel("User ID");
        userIDLabel.setFont(new Font("Arial", Font.BOLD, 18));
        userIDCombo = new JComboBox<>(userIDOptions);

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

        this.add(userIDLabel, "cell 0 2, wrap");
        this.add(userIDCombo, "cell 0 3, width 100%, wrap");

        this.add(deleteButton, "align right");

        productIDLabel.setVisible(true);
        productIDCombo.setVisible(true);
        transactionIDLabel.setVisible(false);
        transactionIDCombo.setVisible(false);
        userIDLabel.setVisible(false);
        userIDCombo.setVisible(false);
    }

    private void onDropdownSelected(String selected) {
        productIDLabel.setVisible(false);
        productIDCombo.setVisible(false);
        transactionIDLabel.setVisible(false);
        transactionIDCombo.setVisible(false);
        userIDLabel.setVisible(false);
        userIDCombo.setVisible(false);

        if (selected.equals("Data")) {
            productIDLabel.setVisible(true);
            productIDCombo.setVisible(true);
        } else if (selected.equals("Transaction")) {
            transactionIDLabel.setVisible(true);
            transactionIDCombo.setVisible(true);
        } else if (selected.equals("User")) {
            userIDLabel.setVisible(true);
            userIDCombo.setVisible(true);
        }
    }

    private void onButtonClick() {
        boolean deleteSuccessful = false;

        String selected = dropdown.getSelectedItem().toString();

        if (selected.equals("Data")) {
            if (productIDCombo.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please choose a Product ID to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Product product = pData.getProductByID(Integer.parseInt(productIDCombo.getSelectedItem().toString()));
            tData.deleteAllTransactionsByProductID(product.getProductID());
            deleteSuccessful = pData.deleteProduct(product.getProductID());

        } else if (selected.equals("Transaction")) {
            if (transactionIDCombo.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please choose a Transaction ID to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
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

        } else if (selected.equals("User")) {
            if (userIDCombo.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please choose a User ID to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int userID = Integer.parseInt(userIDCombo.getSelectedItem().toString());
            deleteSuccessful = uData.deleteUser(userID);
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

        if (userIDCombo.isVisible() && userIDCombo.getSelectedItem() != null) {
            userIDCombo.removeItem(userIDCombo.getSelectedItem());
            if (userIDCombo.getItemCount() > 0) {
                userIDCombo.setSelectedIndex(0);
            }
        }
    }

    public void setProductIDOptions(String[] options) {
        this.productIDOptions = options;
        this.productIDCombo.setModel(new DefaultComboBoxModel<>(productIDOptions));
    }

    public void setTransactionIDOptions(String[] options) {
        this.transactionIDOptions = options;
        this.transactionIDCombo.setModel(new DefaultComboBoxModel<>(transactionIDOptions));
    }

    public void setUserIDOptions(String[] options) {
        this.userIDOptions = options;
        this.userIDCombo.setModel(new DefaultComboBoxModel<>(userIDOptions));
    }
}
