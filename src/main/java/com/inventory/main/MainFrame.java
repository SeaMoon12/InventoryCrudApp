package com.inventory.main;

import com.inventory.pages.DashboardPage;
import com.inventory.pages.LoginPage;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MainFrame extends javax.swing.JFrame {

    private LoginPage loginPage = new LoginPage();
    private DashboardPage dashboardPage = new DashboardPage();

    public MainFrame() {
        setup();

        this.add(loginPage, "align center center");
        this.add(dashboardPage, "pos 0 0 100% 100%");

        // set Initial visibility
        this.loginPage.setVisible(true);
        this.dashboardPage.setVisible(false);

        loginPage.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    if (loginPage.getUsername().equals("admin") && loginPage.getPassword().equals("1234")) {
                        loginPage.setVisible(false);
                        dashboardPage.setVisible(true);
                        // set dashboard visibility to true
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    private void setup() {
        this.setTitle("Inventory Management System");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new MigLayout("debug, fill, hidemode 3, insets 0"));
    }
}
