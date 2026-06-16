package com.inventory.component.dashboard;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {

    private PageNavigatorListener navListener;

    private SidePanelTitle title;
    private SidePanelButton dashboard;
    private SidePanelButton create;
    private SidePanelButton update;
    private SidePanelButton delete;
    private SidePanelButton logout;

    public SidePanel() {
        this.setLayout(new MigLayout("insets 0, gapy 1:1:1, fillx"));

        this.title = new SidePanelTitle();

        this.dashboard = new SidePanelButton();
        this.dashboard.setButtonName("Dashboard");
        this.dashboard.performMousePressed();

        this.create = new SidePanelButton();
        this.create.setButtonName("Create");

        this.update = new SidePanelButton();
        this.update.setButtonName("Update");

        this.delete = new SidePanelButton();
        this.delete.setButtonName("Delete");

        this.logout = new SidePanelButton();
        this.logout.setButtonName("Logout");

        this.add(title, "height 10%, wrap, grow");
        this.add(dashboard, "height 7%, wrap, grow");
        this.add(create, "height 7%, wrap, grow");
        this.add(update, "height 7%, wrap, grow");
        this.add(delete, "height 7%, wrap, grow");
        this.add(logout, "height 7%, wrap, grow");

        dashboard.setListener(new SidePanelButtonListener() {
            @Override
            public void onButtonClick(SidePanelButton button) {
                // when the dashboard button is clicked, we want to update the main page to show the dashboard page
                // send to Dashboard.java -> MainPage.java
                performActionOnClick(button.getButtonName().trim());
            }
        });

        create.setListener(new SidePanelButtonListener() {
            @Override
            public void onButtonClick(SidePanelButton button) {
                performActionOnClick(button.getButtonName().trim());
            }
        });

        update.setListener(new SidePanelButtonListener() {
            @Override
            public void onButtonClick(SidePanelButton button) {
                performActionOnClick(button.getButtonName().trim());
            }
        });

        delete.setListener(new SidePanelButtonListener() {
            @Override
            public void onButtonClick(SidePanelButton button) {
                performActionOnClick(button.getButtonName().trim());
            }
        });

        logout.setListener(new SidePanelButtonListener() {
            @Override
            public void onButtonClick(SidePanelButton button) {
                performActionOnClick(button.getButtonName().trim());
                button.deselect();
                dashboard.performMousePressed();
            }
        });
    }

    public void setNavListener(PageNavigatorListener listener) {
        this.navListener = listener;
    }

    private void performActionOnClick(String button) {
        if (this.navListener != null) {
            this.navListener.onNavigate(button);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        Color colorTopRight = new Color(0x8B41BF);
        Color colorBottomLeft = new Color(0x0e1938);
        GradientPaint colorGrad = new GradientPaint(getWidth(), 0, colorTopRight, 0, getHeight(), colorBottomLeft);
        g2D.setPaint(colorGrad);
        g2D.fillRect(0, 0, getWidth(), getHeight());
    }

    public void setCreateVisible(boolean visible) {
        create.setVisible(visible);
    }

    public void setUpdateVisible(boolean visible) {
        update.setVisible(visible);
    }

    public void setDeleteVisible(boolean visible) {
        delete.setVisible(visible);
    }
}
