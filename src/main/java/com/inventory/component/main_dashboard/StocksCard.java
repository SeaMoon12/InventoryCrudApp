package com.inventory.component.main_dashboard;

import com.inventory.component.dashboard.ShadowCard;

import javax.swing.*;
import java.awt.*;

public class StocksCard extends ShadowCard {

    private JLabel totalStock;

    public StocksCard() {
        super();

        totalStock = new JLabel();
        totalStock.setText("27+");
        totalStock.setForeground(Color.DARK_GRAY);

        this.add(totalStock);
    }
}
