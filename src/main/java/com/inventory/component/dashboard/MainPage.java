package com.inventory.component.dashboard;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

// This is where all the components will be added to the main page, such as the dashboard, products, etc.
public class MainPage extends JPanel {
    public MainPage() {
        this.setLayout(new MigLayout());
        this.setBackground(Color.GREEN);

    }
}
