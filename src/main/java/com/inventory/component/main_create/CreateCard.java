package com.inventory.component.main_create;

import com.inventory.component.LightGreyTextField;
import com.inventory.component.dashboard.ShadowCard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class CreateCard extends ShadowCard {

    private JLabel productName;
    private LightGreyTextField productNameText;

    private JLabel category;
    private LightGreyTextField categoryText;

    private JLabel stock;
    private LightGreyTextField stockText;

    private JButton createButton;

    public CreateCard() {
        this.setLayout(new MigLayout("insets 25"));
        productName = new JLabel("Product Name");
        productName.setFont(new Font("Arial", Font.BOLD, 18));
        productNameText = new LightGreyTextField();

        category = new JLabel("Category");
        category.setFont(new Font("Arial", Font.BOLD, 18));
        categoryText = new LightGreyTextField();

        stock = new JLabel("Stock");
        stock.setFont(new Font("Arial", Font.BOLD, 18));
        stockText = new LightGreyTextField();

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
