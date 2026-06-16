package com.inventory.component.login;

import com.inventory.main.DatabaseConnection;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginComponents extends JPanel {

    private final int LOGO_IMAGE_WIDTH = 1594;
    private final int LOGO_IMAGE_HEIGHT = 1567;
    private final double LOGO_IMAGE_SCALE = 0.15;

    private LoginButtonListener loginButtonListener;

    private ImageIcon logoLogin = new ImageIcon("src/main/resources/images/logo_login.png");
    private JLabel logoLabel = new JLabel();

    private LoginCard loginCard = new LoginCard();

    public LoginComponents() {
        this.setLayout(new MigLayout("fillx, insets 0"));
        this.setOpaque(false);

        Image scaledIcon = logoLogin.getImage().getScaledInstance((int) (LOGO_IMAGE_WIDTH * LOGO_IMAGE_SCALE), (int) (LOGO_IMAGE_HEIGHT * LOGO_IMAGE_SCALE), Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(scaledIcon));

        this.add(logoLabel, "align center, wrap");
        this.add(loginCard, "align center, width 100%");

        loginCard.addEvent(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 performLoginAction(loginCard.getUsername(), loginCard.getPassword());
            }
        });
    }

    private void performLoginAction(String username, String password) {
        if (this.loginButtonListener == null) return;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT role FROM Users WHERE username = ? AND password = ?")) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    this.loginButtonListener.onLoginButtonClick(username, password, role);
                } else {
                    this.loginButtonListener.onLoginButtonClick(username, password, null);
                }
            }
        } catch (SQLException e) {
            System.out.println("Login query failed: " + e.getMessage());
            this.loginButtonListener.onLoginButtonClick(username, password, null);
        }
    }

    public void setLoginButtonListener(LoginButtonListener loginButtonListener) {
        this.loginButtonListener = loginButtonListener;
    }

    public LoginCard getloginCard() {
        return loginCard;
    }
}
