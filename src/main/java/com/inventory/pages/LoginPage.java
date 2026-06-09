package com.inventory.pages;

import com.inventory.component.login.LoginButtonListener;
import com.inventory.component.login.LoginComponents;
import net.miginfocom.swing.MigLayout;

import java.awt.*;

public class LoginPage extends javax.swing.JPanel {

    private LoginButtonListener loginButtonListener;

    private LoginComponents loginComponents = new LoginComponents();

    public LoginPage() {
        this.setLayout(new MigLayout("fill, insets 0, ax center, ay center"));
        this.add(loginComponents, "width 580, center");

        loginComponents.setLoginButtonListener(new LoginButtonListener() {
            @Override
            public void onLoginButtonClick(String username, String password) {
                onButtonClicked(username, password);
            }
        });
    }

    private void onButtonClicked(String username, String password) {
        if (loginButtonListener != null) {
            loginButtonListener.onLoginButtonClick(username, password);
        }
    }

    public void setLoginButtonListener(LoginButtonListener loginButtonListener) {
        this.loginButtonListener = loginButtonListener;
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

    public LoginComponents getLoginComponents() {
        return loginComponents;
    }
}

