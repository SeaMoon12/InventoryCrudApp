package com.inventory.component.main_dashboard;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class DashboardPageContents extends JPanel {

    private JLabel titleLabel;
    private HistoryCard stockHistory;

    public DashboardPageContents() {
        this.setLayout(new MigLayout("gap 8"));

        titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        stockHistory = new HistoryCard();

        this.add(titleLabel, "wrap");
        this.add(stockHistory, "width 100%, height 100%");
    }

    public HistoryCard getStockHistory() {
        return stockHistory;
    }
}
