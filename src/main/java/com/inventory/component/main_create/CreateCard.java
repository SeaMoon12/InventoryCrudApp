package com.inventory.component.main_create;

import com.inventory.component.CustomTextField;
import com.inventory.component.dashboard.ShadowCard;
import com.inventory.queries.ProductData;
import com.inventory.queries.TransactionData;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCard extends ShadowCard {

    private ProductData pData = new ProductData();
    private TransactionData tData = new TransactionData();

    private JLabel productName;
    private CustomTextField productNameText;

    private JLabel category;
    private CustomTextField categoryText;

    private JLabel quantity;
    private CustomTextField quantityText;

    private String[] transactionTypes = {"Incoming", "Outgoing"};
    private JLabel type;
    private JComboBox<String> typeCombo;

    private JButton createButton;

    public CreateCard() {
        this.setLayout(new MigLayout("insets 25"));
        initComponents();
        addComponents();
    }

    private void initComponents() {
        productName = new JLabel("Product Name");
        productName.setFont(new Font("Arial", Font.BOLD, 18));
        productNameText = new CustomTextField(new Color(0xd9d9d9), "Enter product name...");

        category = new JLabel("Category");
        category.setFont(new Font("Arial", Font.BOLD, 18));
        categoryText = new CustomTextField(new Color(0xd9d9d9), "Enter product category...");

        quantity = new JLabel("Quantity");
        quantity.setFont(new Font("Arial", Font.BOLD, 18));
        quantityText = new CustomTextField(new Color(0xd9d9d9), "Enter quantity transacted...");

        type = new JLabel("Type");
        type.setFont(new Font("Arial", Font.BOLD, 18));
        typeCombo = new JComboBox<String>(transactionTypes);

        createButton = new JButton("Add Data");

        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonClicked();
            }
        });
    }

    private void addComponents() {
        this.add(productName, "wrap");
        this.add(productNameText, "width 90%, wrap");

        this.add(category, "wrap, gapy 5");
        this.add(categoryText, "width 90%, wrap");

        this.add(quantity, "wrap, gapy 5");
        this.add(quantityText, "width 90%, wrap");

        this.add(type, "wrap, gapy 5");
        this.add(typeCombo, "width 90%, wrap");

        this.add(createButton, "align right");

    }

    private void onButtonClicked() {
        if (
                productNameText.getText().isEmpty() ||
                categoryText.getText().isEmpty() ||
                quantityText.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                insertData(productNameText.getText(), categoryText.getText(), Integer.parseInt(quantityText.getText()), (String) typeCombo.getSelectedItem());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a number in the Quantity field.",  "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void insertData(String name, String category, int quantity, String type) {
        boolean insertProductSuccessful;
        boolean insertTransactionSuccessful;
        int productID;
        boolean isIncoming;

        if (type.equals("Incoming")) { // set type to boolean
            isIncoming = true;
        } else {
            isIncoming = false;
        }

        if (pData.getProductIDByName(name) == -1) { // if name doesnt exist: insert new product and get productID
            insertProductSuccessful = pData.insertProduct(name, category, 0);
            productID = pData.getProductIDByName(name);
        } else { // if name exists: get productID
            productID = pData.getProductIDByName(name);
        }

        // after get productID, insert to transaction

        insertTransactionSuccessful = tData.insertTransaction(productID, quantity, type);
        insertProductSuccessful = pData.updateStock(productID, quantity, isIncoming);

        if (insertProductSuccessful && insertTransactionSuccessful) {
            JOptionPane.showMessageDialog(this, "Product successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else if (!insertProductSuccessful) {
            JOptionPane.showMessageDialog(this, "Product not added.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Transaction not added.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
