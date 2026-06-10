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
        this.setLayout(new MigLayout("insets 0, ay center", "[shrink] [shrink]", "[shrink]"));
        this.setPreferredSize(new Dimension(200, 50));
        this.setOpaque(false);

        logo = new ImageIcon("src/main/resources/images/logo.png");
        Image rescaledImage = logo.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        logo.setImage(rescaledImage);
        titleLogo.setIcon(logo);

        titleLabel.setText("Gadget Logistics Warehouse");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        this.add(titleLogo, "gapx 5%");
        this.add(titleLabel);
    }
}
