package com.inventory.component;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    private final Color PINK = new Color(0xff66c4);
    private final int roundness = 15;

    public CustomButton(String text) {
        super(text);

        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setForeground(Color.black);

        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g.create();
        g2D.setColor(PINK);
        g2D.fillRoundRect(0, 0, getWidth(), getHeight(), roundness, roundness);

        super.paintComponent(g);
    }
}
