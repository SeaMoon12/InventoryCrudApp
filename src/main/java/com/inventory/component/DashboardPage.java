package com.inventory.component;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardPage extends javax.swing.JPanel implements ActionListener {

    private JLabel tempLabel;

    public DashboardPage() {
        // setup
        this.setLayout(new MigLayout("ax center, ay center"));
        this.tempLabel = new JLabel("Login Success! Welcome to the dashboard.");
        this.add(this.tempLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
