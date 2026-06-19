package com.inventory.component.main_register;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class RegisterUserPageContents extends JPanel {

    private JLabel titleLabel;
    private RegisterUserCard registerUserCard;

    public RegisterUserPageContents() {
        this.setLayout(new MigLayout());
        titleLabel = new JLabel("Register New User");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        registerUserCard = new RegisterUserCard();

        this.add(titleLabel, "wrap");
        this.add(registerUserCard, "width 100%, height 100%");
    }
}