package com.inventory.main;

import com.inventory.component.login.LoginButtonListener;
import com.inventory.pages.DashboardPage;
import com.inventory.pages.LoginPage;
import com.inventory.pages.LogoutButtonListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends javax.swing.JFrame {

    private LoginPage loginPage = new LoginPage();
    private DashboardPage dashboardPage = new DashboardPage();

    public MainFrame() {
        setup();

        this.add(loginPage, "grow");
        this.add(dashboardPage, "grow");

        // set Initial visibility
        this.loginPage.setVisible(true);
        this.dashboardPage.setVisible(false);

        loginPage.setLoginButtonListener(new LoginButtonListener() {
            @Override
            public void onLoginButtonClick(String username, String password) {
                boolean databaseConnected = DatabaseConnection.getConnection() != null;
                if (username.equals("admin") && password.equals("1234") && databaseConnected) {
                    // Hide login page and show dashboard page
                    loginPage.setVisible(false);
                    dashboardPage.setVisible(true);
                    dashboardPage.getMainPagePanel().getDashboardPageContents().getStockHistory().refreshTableData();
                    loginPage.getLoginComponents().getloginCard().clearFields();
                } else {
                    if (!databaseConnected) {
                        JOptionPane.showMessageDialog(null, "You are not connected to the database!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        dashboardPage.setLogoutButtonListener(new LogoutButtonListener() {

            @Override
            public void onLogout() {
                dashboardPage.setVisible(false);
                loginPage.setVisible(true);
            }
        });
    }

    private void setup() {
        this.setTitle("Inventory Management System");
        this.setSize(1360, 765);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new MigLayout("fill, hidemode 3, insets 0"));
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(1120, 630));
    }
}
