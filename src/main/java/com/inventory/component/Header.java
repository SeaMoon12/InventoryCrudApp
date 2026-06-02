package com.inventory.component;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {

    private JTextField searchField;

    public Header() {
        this.setLayout(new MigLayout("debug"));
        this.setBackground(Color.WHITE);

        this.searchField = new JTextField("Search...");
        this.add(this.searchField);
    }
}
