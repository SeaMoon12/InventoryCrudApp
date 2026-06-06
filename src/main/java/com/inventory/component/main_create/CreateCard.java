package com.inventory.component.main_create;

import com.inventory.component.CustomTextField;
import com.inventory.component.dashboard.ShadowCard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCard extends ShadowCard {

    private JLabel productName;
    private CustomTextField productNameText;

    private JLabel category;
    private CustomTextField categoryText;

    private JLabel quantity;
    private CustomTextField quantityText;

    private String[] transactionTypes = {"Incoming", "Outoing"};
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
            JOptionPane.showMessageDialog(null, "Please enter all fields.");
            return;
        }
    }
}
