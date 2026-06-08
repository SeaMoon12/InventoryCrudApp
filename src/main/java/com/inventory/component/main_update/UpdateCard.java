package com.inventory.component.main_update;

import com.inventory.component.CustomTextField;
import com.inventory.component.RoundedPanel;
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

public class UpdateCard extends ShadowCard {

    private ProductData pData = new ProductData();
    private TransactionData tData = new TransactionData();

    private String[] updateOptions = {"Product", "Transaction"};
    private JLabel whatToUpdate;
    private JComboBox<String> whatToUpdateDropdown;

    // products
    private JLabel productIDLabel;
    private CustomTextField productIDTextField;
    private JPanel productsPanel;
    private JLabel productNameLabel;
    private CustomTextField productNameTextField;
    private JLabel productCategoryLabel;
    private CustomTextField productCategoryTextField;

    // transactions
    private String[] transactionTypeOptions = {"Incoming", "Outgoing"};
    private JLabel transactionIDLabel;
    private CustomTextField transactionIDTextField;
    private JPanel transactionsPanel;
    private JLabel transactionQuantityLabel;
    private CustomTextField transactionQuantityTextField;
    private JLabel transactionTypeLabel;
    private JComboBox<String> transactionTypeCombo;

    private JButton updateButton;

    public UpdateCard() {
        this.setLayout(new MigLayout("fill, insets 25, hidemode 3"));

        initComponents();
        addComponents();

        startInitialComponentVisibility();

        whatToUpdateDropdown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) whatToUpdateDropdown.getSelectedItem();
                if (selected.equals("Product")) {
                    setProductsVisibility(true);
                    setTransactionsVisibility(false);
                } else if (selected.equals("Transaction")) {
                    setTransactionsVisibility(true);
                    setProductsVisibility(false);
                }
            }
        });

        productIDTextField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                autoFillFieldsByProductID();
            }
        });

        transactionIDTextField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                autoFillFieldsByTransactionID();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) whatToUpdateDropdown.getSelectedItem();
                boolean productFieldsEmpty = (productIDTextField.getText().isEmpty() ||
                        productNameTextField.getText().isEmpty() ||
                        productCategoryTextField.getText().isEmpty());
                boolean transactionFieldsEmpty = (transactionIDTextField.getText().isEmpty() ||
                        transactionQuantityTextField.getText().isEmpty());

                if (selected.equals("Product") && !productFieldsEmpty) {
                    handleProductUpdate();
                } else if (selected.equals("Transaction") && !transactionFieldsEmpty) {
                    handleTransactionUpdate();
                } else {
                    JOptionPane.showMessageDialog(UpdateCard.this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void initComponents() {
        // What to update
        whatToUpdate = new JLabel("What to update:");
        whatToUpdate.setFont(new Font("Arial", Font.BOLD, 18));
        whatToUpdateDropdown = new JComboBox<>(updateOptions);

        // Products
        productIDLabel = new JLabel("Product ID");
        productIDLabel.setFont(new Font("Arial", Font.BOLD, 18));
        productIDTextField = new CustomTextField(new Color(0xd9d9d9), "Enter product ID...");

        productsPanel = new RoundedPanel();
        productsPanel.setLayout(new MigLayout("insets 7"));

        productNameLabel = new JLabel("Product Name");
        productNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        productNameTextField = new CustomTextField(Color.WHITE, "Enter product name...");

        productCategoryLabel = new JLabel("Product Category");
        productCategoryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        productCategoryTextField = new CustomTextField(Color.WHITE, "Enter product category...");

        // Transactions
        transactionIDLabel = new JLabel("Transaction ID");
        transactionIDLabel.setFont(new Font("Arial", Font.BOLD, 18));
        transactionIDTextField = new CustomTextField(new Color(0xd9d9d9), "Enter transaction ID...");

        transactionsPanel = new RoundedPanel();
        transactionsPanel.setLayout(new MigLayout("insets 7"));

        transactionQuantityLabel = new JLabel("Quantity");
        transactionQuantityLabel.setFont(new Font("Arial", Font.BOLD, 18));
        transactionQuantityTextField = new CustomTextField(Color.WHITE, "Enter quantity...");

        transactionTypeLabel = new JLabel("Transaction Type");
        transactionTypeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        transactionTypeCombo = new JComboBox<>(transactionTypeOptions);
    }

    private void addComponents() {
        this.add(whatToUpdate, "wrap");
        this.add(whatToUpdateDropdown, "width 100%, wrap");

        this.add(productIDLabel, "wrap");
        this.add(productIDTextField, "width 100%, wrap");

        this.add(transactionIDLabel, "wrap");
        this.add(transactionIDTextField, "width 100%, wrap");

        addUpdateDetailsPanels();
        this.add(productsPanel, "width 100%, height 100%, wrap");
        this.add(transactionsPanel, "width 100%, height 100%, wrap");

        updateButton = new JButton("Update");
        this.add(updateButton, "align right");
    }

    private void addUpdateDetailsPanels() {
        // Products Panel
        productsPanel.add(productNameLabel, "wrap");
        productsPanel.add(productNameTextField, "width 100%, wrap");
        productsPanel.add(productCategoryLabel, "wrap");
        productsPanel.add(productCategoryTextField, "width 100%, wrap");

        // Transactions Panel
        transactionsPanel.add(transactionQuantityLabel, "wrap");
        transactionsPanel.add(transactionQuantityTextField, "width 100%, wrap");
        transactionsPanel.add(transactionTypeLabel, "wrap");
        transactionsPanel.add(transactionTypeCombo, "width 100%, wrap");
    }

    private void startInitialComponentVisibility() {
        setProductsVisibility(true);
        setTransactionsVisibility(false);
    }

    private void setProductsVisibility(boolean visibility) {
        if (visibility) {
            productsPanel.setVisible(true);
            productIDLabel.setVisible(true);
            productIDTextField.setVisible(true);
        } else {
            productsPanel.setVisible(false);
            productIDLabel.setVisible(false);
            productIDTextField.setVisible(false);
        }
    }

    private void setTransactionsVisibility(boolean visibility) {
        if (visibility) {
            transactionsPanel.setVisible(true);
            transactionIDLabel.setVisible(true);
            transactionIDTextField.setVisible(true);
        } else {
            transactionsPanel.setVisible(false);
            transactionIDLabel.setVisible(false);
            transactionIDTextField.setVisible(false);
        }
    }

    private void autoFillFieldsByProductID() {
        String productName = pData.getProductByID(Integer.parseInt(productIDTextField.getText())).getName();
        String productCategory = pData.getProductByID(Integer.parseInt(productIDTextField.getText())).getCategory();

        productNameTextField.setText(productName);
        productCategoryTextField.setText(productCategory);
    }

    private void autoFillFieldsByTransactionID() {
        int quantity = tData.getTransactionByID(Integer.parseInt(transactionIDTextField.getText())).getQuantity();
        String type = tData.getTransactionByID(Integer.parseInt(transactionIDTextField.getText())).getTransactionType();

        transactionQuantityTextField.setText(String.valueOf(quantity));
        if (type.equals("Incoming")) {
            transactionTypeCombo.setSelectedIndex(0);
        } else {
            transactionTypeCombo.setSelectedIndex(1);
        }
    }

    private void handleProductUpdate() {
        boolean isSuccessful;
        // does the new/updated productID exist?
        boolean nameExists;
        String newName = productNameTextField.getText();
        int existingProductID = pData.getProductIDByName(newName);

        try {
            if (existingProductID != -1 && existingProductID != Integer.parseInt(productIDTextField.getText())) {
                nameExists = true;
            } else {
                nameExists = false;
            }

            int typoProductStock = pData.getProductByID(Integer.parseInt(productIDTextField.getText())).getStock();
            String type = tData.getTransactionByID(tData.getTransactionIDByProductID(Integer.parseInt(productIDTextField.getText()))).getTransactionType();

            boolean isIncoming = type.equals("Incoming");

            if (nameExists) {
                // updated name exists (it is a typo): add/subt existing product stock with the typo one based on typo's type then delete typo product
                boolean stockMoved = pData.updateStock(existingProductID, typoProductStock, isIncoming);
                boolean transactionInserted = tData.insertTransaction(existingProductID, typoProductStock, type);
                boolean oldTransactionDeleted = tData.deleteTransaction(tData.getTransactionIDByProductID(Integer.parseInt(productIDTextField.getText())));
                boolean productDeleted = pData.deleteProduct(Integer.parseInt(productIDTextField.getText()));
                isSuccessful = stockMoved && productDeleted && transactionInserted && oldTransactionDeleted;
            } else {
                // Name is not a typo: just update normally
                isSuccessful = pData.updateProductDetails(Integer.parseInt(productIDTextField.getText()), newName, productCategoryTextField.getText());
            }

            if (isSuccessful) {
                JOptionPane.showMessageDialog(null, "Product Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter an integer for integer fields!");
        }
    }

    private void handleTransactionUpdate() {
        boolean isQuantityPositive;
        int quantityChange;

        try {
            Transaction oldTransaction = tData.getTransactionByID(Integer.parseInt(transactionIDTextField.getText()));
            int oldQuantity = oldTransaction.getQuantity();
            String oldType = oldTransaction.getTransactionType();
            int newQuantity = Integer.parseInt(transactionQuantityTextField.getText());
            String newType = (String) transactionTypeCombo.getSelectedItem();

            int productStock = pData.getProductByID(oldTransaction.getProductID()).getStock();
            int productID = pData.getProductByID(oldTransaction.getProductID()).getProductID();

            if (oldType.equals("Incoming") && newType.equals("Incoming")) {
                quantityChange = newQuantity - oldQuantity;
            } else if (oldType.equals("Outgoing") && newType.equals("Outgoing")) {
                quantityChange = oldQuantity - newQuantity;
            } else {
                quantityChange = oldQuantity + newQuantity;
                pData.updateStock(productID, quantityChange, !oldType.equals("Incoming") || !newType.equals("Outgoing"));
            }

            isQuantityPositive = quantityChange > 0;

            if (!isQuantityPositive && productStock < Math.abs(quantityChange)) {
                JOptionPane.showMessageDialog(UpdateCard.this, "Current stock is too small!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            pData.updateStock(productID, Math.abs(quantityChange), isQuantityPositive);
            boolean updateSuccess = tData.updateTransaction(oldTransaction.getTransactionID(), newQuantity, newType);

            if (updateSuccess) {
                JOptionPane.showMessageDialog(null, "Transaction updated successfully!", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Transaction update failed!", "Information", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter an integer for integer fields!");
            e.printStackTrace();
        }
    }
}
