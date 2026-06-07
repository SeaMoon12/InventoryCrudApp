package com.inventory.component.main_update;

import com.inventory.component.CustomTextField;
import com.inventory.component.RoundedPanel;
import com.inventory.component.dashboard.ShadowCard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class UpdateCard extends ShadowCard {

    private JLabel transactionID;
    private CustomTextField transactionIDText;
    private JLabel productID;
    private CustomTextField productIDText;

    private String[] transactionTypes = {"Incoming", "Outoing"};
    private JPanel updateDetailsPanel;
    private JLabel transactionType;
    private JComboBox<String> transactionTypeCombo;
    private JLabel quantity;
    private CustomTextField quantityText;
    private JLabel productName;
    private CustomTextField productNameText;
    private JLabel category;
    private CustomTextField categoryText;
    private JButton updateButton;

    public UpdateCard() {
        this.setLayout(new MigLayout("insets 25"));

        initComponents();
        addComponents();
    }

    private void initComponents() {
        transactionID = new JLabel("Transaction ID");
        transactionID.setFont(new Font("Arial", Font.BOLD, 18));
        transactionIDText = new CustomTextField(new Color(0xd9d9d9), "Enter transaction ID...");

        productID = new JLabel("Product ID");
        productID.setFont(new Font("Arial", Font.BOLD, 18));
        productIDText = new CustomTextField(new Color(0xd9d9d9), "Enter product ID...");

        updateDetailsPanel = new RoundedPanel();
        updateDetailsPanel.setLayout(new MigLayout("insets 15"));
        updateDetailsPanel.setBackground(new Color(0xd9d9d9));

        transactionType = new JLabel("Transaction Type");
        transactionType.setFont(new Font("Arial", Font.BOLD, 14));
        transactionTypeCombo = new JComboBox<>(transactionTypes);

        quantity = new JLabel("Quantity");
        quantity.setFont(new Font("Arial", Font.BOLD, 14));
        quantityText = new CustomTextField(Color.WHITE, "Enter transaction quantity...");

        productName = new JLabel("Product Name");
        productName.setFont(new Font("Arial", Font.BOLD, 14));
        productNameText = new CustomTextField(Color.WHITE, "Enter product name...");

        category = new JLabel("Category");
        category.setFont(new Font("Arial", Font.BOLD, 14));
        categoryText = new CustomTextField(Color.WHITE, "Enter product category...");

        updateButton = new JButton("Update Data");
    }

    private void addComponents() {
        updateDetailsPanel.add(transactionID, "width 100%, wrap");
        updateDetailsPanel.add(transactionIDText, "width 100%, wrap");
        updateDetailsPanel.add(transactionType, "width 100%, wrap");
        updateDetailsPanel.add(transactionTypeCombo, "width 100%, wrap");
        updateDetailsPanel.add(quantity, "width 100%, wrap");
        updateDetailsPanel.add(quantityText, "width 100%, wrap");
        updateDetailsPanel.add(productName, "width 100%, wrap");
        updateDetailsPanel.add(productNameText, "width 100%, wrap");
        updateDetailsPanel.add(category, "width 100%, wrap");
        updateDetailsPanel.add(categoryText, "width 100%, wrap");

        this.add(transactionID, "wrap");
        this.add(transactionIDText, "width 100%, wrap");
        this.add(productID, "wrap");
        this.add(productIDText, "width 100%, wrap");
        this.add(updateDetailsPanel, "width 100%, height 100%, wrap");
        this.add(updateButton, "align right");
    }
}
