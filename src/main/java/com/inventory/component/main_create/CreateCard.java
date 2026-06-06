package com.inventory.component.main_create;

import com.inventory.component.CustomTextField;
import com.inventory.component.dashboard.ShadowCard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class CreateCard extends ShadowCard {

    private JLabel productName;
    private CustomTextField productNameText;

    private JLabel category;
    private CustomTextField categoryText;

    private JLabel stock;
    private CustomTextField stockText;

    private JButton createButton;

    public CreateCard() {
        this.setLayout(new MigLayout("insets 25"));
        productName = new JLabel("Product Name");
        productName.setFont(new Font("Arial", Font.BOLD, 18));
        productNameText = new CustomTextField(new Color(0xd9d9d9), "Enter product name...");

        category = new JLabel("Category");
        category.setFont(new Font("Arial", Font.BOLD, 18));
        categoryText = new CustomTextField(new Color(0xd9d9d9), "Enter product category...");

        stock = new JLabel("Stock");
        stock.setFont(new Font("Arial", Font.BOLD, 18));
        stockText = new CustomTextField(new Color(0xd9d9d9), "Enter product stock...");

        createButton = new JButton("Add Data");

        this.add(productName, "wrap");
        this.add(productNameText, "width 90%, wrap");

        this.add(category, "wrap, gapy 5");
        this.add(categoryText, "width 90%, wrap");

        this.add(stock, "wrap, gapy 5");
        this.add(stockText, "width 90%, wrap");

        this.add(createButton, "align right");
    }
}
