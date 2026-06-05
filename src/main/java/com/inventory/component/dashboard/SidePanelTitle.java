package com.inventory.component.dashboard;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SidePanelTitle extends JPanel {

    private ImageIcon logo;
    private JLabel titleLogo = new JLabel();
    private JLabel titleLabel = new JLabel();

    public SidePanelTitle() {
        this.setLayout(new MigLayout());
        this.setPreferredSize(new Dimension(200, 50));
        this.setOpaque(false);

        logo = new ImageIcon("src/main/resources/images/logo.png");
        titleLogo.setIcon(logo);

        titleLabel.setText("IMS");

        this.add(titleLogo);
        this.add(titleLabel);
    }
}
