package com.inventory.component.dashboard;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class SidePanelButton extends JPanel {

    private JLabel buttonName;

    public SidePanelButton() {
        this.setLayout(new MigLayout());
        this.setPreferredSize(new Dimension(200, 50));
        this.setBackground(Color.RED);

        this.buttonName = new JLabel("Button Name");
        this.add(this.buttonName);
    }

    public void setButtonName(String buttonName) {
        this.buttonName.setText(buttonName);
    }

}
