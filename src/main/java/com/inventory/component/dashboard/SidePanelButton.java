package com.inventory.component.dashboard;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SidePanelButton extends JPanel {

    private SidePanelButtonListener listener;

    private static SidePanelButton activeButton = null;
    private JLabel buttonName;
    private boolean isSelected;
    private Color defaultColor = new Color(0x795fbe);
    private Color selectedColor = new Color(0x8c78c1);

    public SidePanelButton() {
        this.setLayout(new MigLayout("fill"));
        this.setPreferredSize(new Dimension(200, 50));
        this.setBackground(defaultColor);

        this.buttonName = new JLabel("Button Name");
        this.buttonName.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        this.buttonName.setForeground(Color.white);
        this.add(this.buttonName);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                performMousePressed();
            }
        });
    }

    public void setButtonName(String buttonName) {
        this.buttonName.setText(buttonName);
    }

    public void setListener(SidePanelButtonListener listener) {
        this.listener = listener;
    }

    public void performMousePressed() {
        if (activeButton != null && activeButton != this) {
            activeButton.deselect();
        }

        this.isSelected = true;
        this.setBackground(selectedColor);

        activeButton = this;

        // when this button is pressed, we want to update the main page to show the corresponding page
        // send to SidePanel.java -> Dashboard.java -> MainPage.java
        if (this.listener != null) {
            this.listener.onButtonClick(this);
        }
    }

    public void deselect() {
        this.isSelected = false;
        this.setBackground(defaultColor);
    }

    public String getButtonName() {
        return this.buttonName.getText();
    }
}
