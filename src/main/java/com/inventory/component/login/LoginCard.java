package com.inventory.component.login;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginCard extends JPanel implements ActionListener {

    private ActionListener actionListener;

    private JButton loginButton;
    private LoginTextField usernameField;
    private LoginPasswordField passwordField;

    public LoginCard() {
        this.setLayout(new MigLayout("fillx, insets 10, gapy 3"));
        this.setOpaque(false);

        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

        usernameField = new LoginTextField();
        usernameField.setPreferredSize(new Dimension(200, 30));

        passwordField = new LoginPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        // Add components to panel
        this.add(usernameLabel, "wrap");
        this.add(usernameField, "grow, wrap");

        this.add(passwordLabel, "wrap");
        this.add(passwordField, "grow, wrap");

        this.add(loginButton, "center, gapy 30");
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        g2D.setColor(new Color(0xd9d9d9));
        g2D.fillRoundRect(0, 0, width, height, 10, 10);
    }
}
