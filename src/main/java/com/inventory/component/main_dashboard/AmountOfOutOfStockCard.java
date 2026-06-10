package com.inventory.component.main_dashboard;

import com.inventory.component.dashboard.ShadowCard;
import com.inventory.queries.Dashboard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class AmountOfOutOfStockCard extends ShadowCard {

    private JLabel amountOutOfStockItemsLabel;

    public AmountOfOutOfStockCard() {
        super();
        this.setLayout(new MigLayout("insets 25, ax center, ay center"));
        amountOutOfStockItemsLabel = new JLabel("Out of stock items: " + new Dashboard().getOutOfStockCount());
        amountOutOfStockItemsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        amountOutOfStockItemsLabel.setForeground(new Color(0xff66c4));

        this.add(amountOutOfStockItemsLabel);
    }
}
