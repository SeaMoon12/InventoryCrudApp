package com.inventory.component.dashboard;

import javax.swing.*;

public class SidePanel extends JPanel {

    private SidePanelButton dashboard;
    private SidePanelButton products;
    private SidePanelButton button3;

    public SidePanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.dashboard = new SidePanelButton();
        this.dashboard.setButtonName("Dashboard"); // temporary

        this.products = new SidePanelButton();
        this.products.setButtonName("Products"); // temporary

        this.button3 = new SidePanelButton();
        this.button3.setButtonName("Button3");

        this.add(dashboard);
        this.add(products);
        this.add(button3);

        this.setVisible(true);
    }
}
