package com.inventory.main;

import com.inventory.component.login.LoginButtonListener;
import com.inventory.pages.DashboardPage;
import com.inventory.pages.LoginPage;
import com.inventory.pages.LogoutButtonListener;
import com.inventory.pages.RegisterPage;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends javax.swing.JFrame {

    private LoginPage loginPage = new LoginPage();
    private DashboardPage dashboardPage = new DashboardPage();
    private RegisterPage registerPage = new RegisterPage();

    public MainFrame() {
        setup();

        this.add(loginPage, "grow");
        this.add(dashboardPage, "grow");

        this.add(registerPage, "grow");
        registerPage.setVisible(false);

        // set Initial visibility
        this.loginPage.setVisible(true);
        this.dashboardPage.setVisible(false);

        loginPage.setLoginButtonListener(new LoginButtonListener() {
            @Override
            public void onLoginButtonClick(String username, String password, String role) {
                boolean databaseConnected = DatabaseConnection.getConnection() != null;
                boolean credentialsValid = role != null;

                if (credentialsValid && databaseConnected) {
                    loginPage.setVisible(false);
                    dashboardPage.setVisible(true);
                    dashboardPage.applyRolePermissions(role);
                    dashboardPage.getMainPagePanel().getDashboardPageContents().getStockHistory().refreshTableData();
                    loginPage.getLoginComponents().getloginCard().clearFields();
                } else {
                    if (!databaseConnected) {
                        JOptionPane.showMessageDialog(null, "You are not connected to the database!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }

                loginPage.getLoginComponents().getloginCard().addEvent(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if ("SHOW_REGISTER".equals(e.getActionCommand())) {
                            loginPage.setVisible(false);
                            registerPage.setVisible(true);
                        }
                    }
                });
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
        this.setMinimumSize(new Dimension(1360, 765));
    }
}
