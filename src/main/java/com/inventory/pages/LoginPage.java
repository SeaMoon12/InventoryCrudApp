package com.inventory.pages;

import com.inventory.component.dashboard.SidePanelButton;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends javax.swing.JPanel implements ActionListener {

    private ActionListener actionListener;

    private JButton loginButton;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {
        // i wanna see the page borders
        // this.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));

        // setup
        this.setLayout(new MigLayout("ax center, ay center, insets 0"));
        this.setSize(400, 300);

        // Setup Components
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 30));

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        // Add components to panel
        this.add(usernameLabel);
        this.add(usernameField, "wrap");

        this.add(passwordLabel);
        this.add(passwordField, "wrap");

        this.add(loginButton, "span, center");
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public void addEvent(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            actionListener.actionPerformed(e);
        }
    }
}

