package com.inventory.component.main_create;

import com.inventory.component.dashboard.ShadowCard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class CreateCard extends ShadowCard {

    private JLabel productName;
    private JTextField productNameText;

    private JLabel category;
    private JTextField categoryText;

    private JLabel stock;
    private JTextField stockText;

    private JButton createButton;

    public CreateCard() {
        this.setLayout(new MigLayout("insets 30"));
        productName = new JLabel("Product Name");
        productNameText = new JTextField();
        category = new JLabel("Category");
        categoryText = new JTextField();
        stock = new JLabel("Stock");
        stockText = new JTextField();
        createButton = new JButton("Create");

        this.add(productName, "wrap");
        this.add(productNameText, "wrap");

        this.add(category, "wrap");
        this.add(categoryText, "wrap");

        this.add(stock, "wrap");
        this.add(stockText, "wrap");

        this.add(createButton, "wrap");
    }
}
