package com.inventory.component;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends javax.swing.JPanel {
    public LoginPage() {
        // i wanna see the page borders
//        this.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));

        // setup
        this.setLayout(new MigLayout("ax center, ay center"));
        this.setSize(400, 300);

        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 30));

        JTextField passwordField = new JTextField();
        passwordField.setPreferredSize(new Dimension(200, 30));

        JButton loginButton = new JButton("Login");

        this.add(usernameLabel);
        this.add(usernameField, "wrap");

        this.add(passwordLabel);
        this.add(passwordField, "wrap");

        this.add(loginButton, "span, center");
    }
}
