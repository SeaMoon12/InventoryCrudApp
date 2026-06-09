package com.inventory.component.login;

import javax.swing.*;
import java.awt.*;

public class LoginPasswordField extends JPasswordField {
    private final int roundness = 10;

    private String placeholder;
    private Color placeholderColor = Color.GRAY;
    private Font placeholderFont = new Font("Arial", Font.PLAIN, 12);

    public LoginPasswordField() {
        this.setOpaque(false);
        this.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g.create();

        g2D.setColor(getBackground());
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
