package com.inventory.component;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {
    private final int roundness = 10;
    private Color backgroundColor;

    private String placeholder;
    private Color placeholderColor = Color.GRAY;
    private Font placeholderFont = new Font("Arial", Font.PLAIN, 12);

    public CustomTextField(Color color) {
        this(color, "");
    }

    public CustomTextField(Color color, String placeholder) {
        this.setOpaque(false);
        this.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        this.backgroundColor = color;
        this.placeholder = placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        this.repaint();
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        java.awt.Graphics2D g2D = (java.awt.Graphics2D) g.create();

        g2D.setColor(backgroundColor);
        g2D.fillRoundRect(0, 0, getWidth(), getHeight(), roundness, roundness);
        super.paintComponent(g);

        if (getText().isEmpty() && placeholder != null && !placeholder.isEmpty()) {
            g2D.setColor(placeholderColor);
            g2D.setFont(placeholderFont);

            FontMetrics fm = g2D.getFontMetrics();
            int x = getInsets().left;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

            g2D.drawString(placeholder, x, y);
        }
    }
}
