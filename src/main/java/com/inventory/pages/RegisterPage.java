package com.inventory.pages;

import com.inventory.component.login.RegisterCard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends JPanel {

    private RegisterCard registerCard = new RegisterCard();

    public RegisterPage() {
        this.setLayout(new MigLayout("fill, insets 0, ax center, ay center"));
        this.add(registerCard, "width 580, center");

        registerCard.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRegisterButtonClick();
            }
        });
    }

    private void onRegisterButtonClick() {
        String username = registerCard.getUsername();
        String password = registerCard.getPassword();
        String role     = registerCard.getRole();
        byte[] image    = registerCard.getSelectedImageBytes();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username and password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = registerCard.insertUser(username, password, role, image);
        if (success) {
            JOptionPane.showMessageDialog(null, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            registerCard.clearFields();
        } else {
            JOptionPane.showMessageDialog(null, "Registration failed. Username may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        Color colorTopRight = new Color(0xf359d2);
        Color colorBottomLeft = new Color(0x0e1938);
        GradientPaint colorGrad = new GradientPaint(getWidth(), 0, colorTopRight, 0, getHeight(), colorBottomLeft);
        g2D.setPaint(colorGrad);
        g2D.fillRect(0, 0, getWidth(), getHeight());
    }
}