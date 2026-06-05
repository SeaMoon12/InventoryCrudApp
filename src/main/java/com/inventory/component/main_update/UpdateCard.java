package com.inventory.component.main_update;

import com.inventory.component.LightGreyTextField;
import com.inventory.component.RoundedPanel;
import com.inventory.component.WhiteTextField;
import com.inventory.component.dashboard.ShadowCard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class UpdateCard extends ShadowCard {

    private JLabel productID;
    private LightGreyTextField productIDText;
    private JPanel updateDetailsPanel;
    private JLabel productName;
    private WhiteTextField productNameText;
    private JLabel category;
    private WhiteTextField categoryText;
    private JLabel stock;
    private WhiteTextField stockText;
    private JButton updateButton;

    public UpdateCard() {
        this.setLayout(new MigLayout("insets 25"));

        productID = new JLabel("Product ID");
        productID.setFont(new Font("Arial", Font.BOLD, 18));
        productIDText = new LightGreyTextField();

        updateDetailsPanel = new RoundedPanel();
        updateDetailsPanel.setLayout(new MigLayout("insets 15"));
        updateDetailsPanel.setBackground(new Color(0xd9d9d9));

        productName = new JLabel("Product Name");
        productName.setFont(new Font("Arial", Font.BOLD, 14));
        productNameText = new WhiteTextField();

        category = new JLabel("Category");
        category.setFont(new Font("Arial", Font.BOLD, 14));
        categoryText = new WhiteTextField();

        stock = new JLabel("Stock");
        stock.setFont(new Font("Arial", Font.BOLD, 14));
        stockText = new WhiteTextField();

        updateDetailsPanel.add(productName, "width 100%, wrap");
        updateDetailsPanel.add(productNameText, "width 100%, wrap");
        updateDetailsPanel.add(category, "width 100%, wrap");
        updateDetailsPanel.add(categoryText, "width 100%, wrap");
        updateDetailsPanel.add(stock, "width 100%, wrap");
        updateDetailsPanel.add(stockText, "width 100%, wrap");

        updateButton = new JButton("Update Data");

        this.add(productID, "wrap");
        this.add(productIDText, "width 100%, wrap");
        this.add(updateDetailsPanel, "width 100%, height 100%, wrap");
        this.add(updateButton, "align right");
    }
}
