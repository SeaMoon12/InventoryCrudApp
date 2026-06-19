package com.inventory.main;

import com.inventory.component.login.LoginButtonListener;
import com.inventory.pages.DashboardPage;
import com.inventory.pages.LoginPage;
import com.inventory.pages.LogoutButtonListener;
import com.inventory.queries.UserData;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends javax.swing.JFrame {

    private LoginPage loginPage = new LoginPage();
    private DashboardPage dashboardPage;
    private UserData uData = new UserData();

    public MainFrame() {
        setup();

        this.add(loginPage, "grow");

        this.loginPage.setVisible(true);

        loginPage.setLoginButtonListener(new LoginButtonListener() {
            @Override
            public void onLoginButtonClick(String username, String password) {
                boolean databaseConnected = DatabaseConnection.getConnection() != null;
                if (!databaseConnected) {
                    JOptionPane.showMessageDialog(null, "You are not connected to the database!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                com.inventory.queries.User loggedInUser = uData.getUserByCredentials(username, password);

                if (loggedInUser == null) {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String role = loggedInUser.getRole();

                dashboardPage = new DashboardPage(role, loggedInUser);
                MainFrame.this.add(dashboardPage, "grow");
                MainFrame.this.revalidate();

                loginPage.setVisible(false);
                dashboardPage.setVisible(true);
                dashboardPage.getMainPagePanel().getDashboardPageContents().getStockHistory().refreshTableData();
                loginPage.getLoginComponents().getloginCard().clearFields();

                dashboardPage.setLogoutButtonListener(new LogoutButtonListener() {
                    @Override
                    public void onLogout() {
                        dashboardPage.setVisible(false);
                        MainFrame.this.remove(dashboardPage);
                        loginPage.setVisible(true);
                    }
                });
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
