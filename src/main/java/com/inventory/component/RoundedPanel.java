package com.inventory.component;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {

    private final int roundness = 10;

    public RoundedPanel() {
        this.setOpaque(false);
//        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(new Color(0xd9d9d9));
        g2D.fillRoundRect(0, 0, getWidth(), getHeight(), roundness, roundness);
        super.paintComponent(g);
    }
}
