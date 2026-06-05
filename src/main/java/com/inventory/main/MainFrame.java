package com.inventory.main;

import com.inventory.component.login.LoginButtonListener;
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

        this.add(loginPage, "grow");
        this.add(dashboardPage, "pos 0 0 100% 100%");

        // set Initial visibility
        this.loginPage.setVisible(true);
        this.dashboardPage.setVisible(false);

        loginPage.setLoginButtonListener(new LoginButtonListener() {
            @Override
            public void onLoginButtonClick(String username, String password) {
                if (username.equals("admin") && password.equals("1234")) {
                    // Hide login page and show dashboard page
                    loginPage.setVisible(false);
                    dashboardPage.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setup() {
        this.setTitle("Inventory Management System");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new MigLayout("fill, hidemode 3, insets 0"));
    }
}
