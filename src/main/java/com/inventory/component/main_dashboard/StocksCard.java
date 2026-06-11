package com.inventory.component.main_dashboard;

import com.inventory.component.dashboard.ShadowCard;
import com.inventory.queries.Dashboard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class StocksCard extends ShadowCard {

    private JLabel totalStock;

    public StocksCard() {
        super();
        this.setLayout(new MigLayout("insets 25, ax center, ay center"));

        totalStock = new JLabel();
        totalStock.setText("Total items in stock:" + String.valueOf(new Dashboard().getTotalStock()));
        totalStock.setFont(new Font("Arial", Font.BOLD, 18));
        totalStock.setForeground(new Color(0xff66c4));

        this.add(totalStock);
    }

    public void refresh() {
        totalStock.setText("Total items in stock:" + String.valueOf(new Dashboard().getTotalStock()));
    }
}
