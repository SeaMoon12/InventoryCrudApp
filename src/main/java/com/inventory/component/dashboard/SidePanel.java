package com.inventory.component.dashboard;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {

    private SidePanelTitle title;
    private SidePanelButton dashboard;
    private SidePanelButton products;
    private SidePanelButton button3;

    public SidePanel() {
        this.setLayout(new MigLayout("insets 0, gapy 1:1:1, fillx"));

        this.title = new SidePanelTitle();

        this.dashboard = new SidePanelButton();
        this.dashboard.setButtonName("Dashboard"); // temporary

        this.products = new SidePanelButton();
        this.products.setButtonName("Products"); // temporary

        this.button3 = new SidePanelButton();
        this.button3.setButtonName("Button3");

        this.add(title, "height 10%, wrap, grow");
        this.add(dashboard, "height 10%, wrap, grow");
        this.add(products, "height 10%, wrap, grow");
        this.add(button3, "height 10%, wrap, grow");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        Color colorTopRight = new Color(0x6b41bf);
        Color colorBottomLeft = new Color(0x0e1938);
        GradientPaint colorGrad = new GradientPaint(getWidth(), 0, colorTopRight, 0, getHeight(), colorBottomLeft);
        g2D.setPaint(colorGrad);
        g2D.fillRect(0, 0, getWidth(), getHeight());
    }
}
