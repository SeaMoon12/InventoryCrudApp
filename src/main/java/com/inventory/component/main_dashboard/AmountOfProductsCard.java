package com.inventory.component.main_dashboard;

import com.inventory.component.dashboard.ShadowCard;
import com.inventory.queries.Dashboard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class AmountOfProductsCard extends ShadowCard {

    private JLabel amountOfProductsLabel;

    public AmountOfProductsCard() {
        super();
        this.setLayout(new MigLayout("insets 25, ax center, ay center"));

        amountOfProductsLabel = new JLabel("Products in stock: " + new Dashboard().getTotalProducts());
        amountOfProductsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        amountOfProductsLabel.setForeground(new Color(0xff66c4));

        this.add(amountOfProductsLabel);
    }

    public void refresh() {
        amountOfProductsLabel.setText("Products in stock: " + new Dashboard().getTotalProducts());
    }
}
