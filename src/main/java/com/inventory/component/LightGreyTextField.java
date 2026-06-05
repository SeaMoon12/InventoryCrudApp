package com.inventory.component;

import javax.swing.*;
import java.awt.*;

public class LightGreyTextField extends JTextField {
    private final int roundness = 10;

    public LightGreyTextField() {
        this.setOpaque(false);
        this.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        java.awt.Graphics2D g2D = (java.awt.Graphics2D) g.create();

        g2D.setColor(new Color(0xd9d9d9));
        g2D.fillRoundRect(0, 0, getWidth(), getHeight(), roundness, roundness);
        super.paintComponent(g);
    }
}
