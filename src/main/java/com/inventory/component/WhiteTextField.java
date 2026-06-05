package com.inventory.component;

import javax.swing.*;
import java.awt.*;

public class WhiteTextField extends JTextField {
    private final int roundness = 10;

    public WhiteTextField() {
        this.setOpaque(false);
        this.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g.create();
        g2D.setColor(Color.WHITE);
        g2D.fillRoundRect(0, 0, getWidth(), getHeight(), roundness, roundness);
        super.paintComponent(g);
    }
}
