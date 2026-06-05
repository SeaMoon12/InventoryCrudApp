package com.inventory.component.login;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginComponents extends JPanel {

    private LoginButtonListener loginButtonListener;

    private JLabel title = new JLabel("App Name");
    private LoginCard loginCard = new LoginCard();

    public LoginComponents() {
        this.setLayout(new MigLayout("fillx, insets 0"));
        this.setOpaque(false);

        title.setFont(new Font("Arial", Font.BOLD, 56));

        this.add(title, "align center, wrap");
        this.add(loginCard, "align center, width 100%");

        loginCard.addEvent(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 performLoginAction(loginCard.getUsername(), loginCard.getPassword());
            }
        });
    }

    private void performLoginAction(String username, String password) {
        if (this.loginButtonListener != null) {
            this.loginButtonListener.onLoginButtonClick(username, password);
        }
    }

    public void setLoginButtonListener(LoginButtonListener loginButtonListener) {
        this.loginButtonListener = loginButtonListener;
    }
}
