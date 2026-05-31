package com.inventory.main;

import com.inventory.component.LoginPage;
import net.miginfocom.swing.MigLayout;

public class MainFrame extends javax.swing.JFrame {

    private LoginPage loginPage = new LoginPage();

    public MainFrame() {
        this.setLayout(new MigLayout("debug, ax center, ay center"));
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Inventory Management System");
        this.setSize(800, 600);
        this.setVisible(true);

        this.add(loginPage);
    }
}
