package com.inventory.component.login;

import javax.swing.*;
import java.awt.*;

public class LoginPasswordField extends JPasswordField {
    private final int roundness = 10;

    public LoginPasswordField() {
        this.setOpaque(false);
        this.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g.create();

        g2D.setColor(getBackground());
        g2D.fillRoundRect(0, 0, getWidth(), getHeight(), roundness, roundness);
        super.paintComponent(g);
    }
}
