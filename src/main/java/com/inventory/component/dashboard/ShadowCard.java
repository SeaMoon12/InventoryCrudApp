package com.inventory.component.dashboard;

import javax.swing.*;
import java.awt.*;

public class ShadowCard extends JPanel {

    private final int shadowSize = 8;
    private final int cornerRound = 30;

    public ShadowCard() {
        this.setOpaque(false);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, shadowSize, shadowSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < shadowSize; i++) {
            g2D.setColor(new Color(0, 0, 0, shadowSize - i));

            g2D.fillRoundRect(
                    i, i,
                    width - (i * 2),
                    height - (i * 2),
                    cornerRound, cornerRound
            );
        }

        g2D.setColor(Color.WHITE);
        g2D.fillRoundRect(shadowSize, shadowSize,
                width - (shadowSize * 2),
                height - (shadowSize * 2),
                cornerRound, cornerRound);
    }
}
