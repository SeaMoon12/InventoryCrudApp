package com.inventory.component.dashboard;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class SidePanelTitle extends JPanel {

    private JLabel titleLabel = new JLabel();

    public SidePanelTitle() {
        this.setLayout(new MigLayout("debug"));
        this.setPreferredSize(new Dimension(200, 50));
        this.setBackground(Color.DARK_GRAY);

        titleLabel.setText("Inventory");

        this.add(titleLabel);
        this.setVisible(false);
    }
}
