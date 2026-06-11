package com.inventory.component.main_dashboard;

import com.inventory.component.dashboard.ShadowCard;
import com.inventory.queries.Dashboard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class AmountOFLowStockCard extends ShadowCard {

    private JLabel amountOfLowStockItemsLabel;

    AmountOFLowStockCard() {
        super();
        this.setLayout(new MigLayout("insets 25, ax center, ay center"));
        amountOfLowStockItemsLabel = new JLabel("Low stock items in stock: " + new Dashboard().getLowStockCount());
        amountOfLowStockItemsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        amountOfLowStockItemsLabel.setForeground(new Color(0xff66c4));

        this.add(amountOfLowStockItemsLabel);
    }

    public void refresh() {
        amountOfLowStockItemsLabel.setText("Low stock items in stock: " + new Dashboard().getLowStockCount());
    }
}
