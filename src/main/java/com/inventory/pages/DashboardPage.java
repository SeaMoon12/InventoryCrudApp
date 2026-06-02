package com.inventory.pages;

import com.inventory.component.dashboard.MainPage;
import com.inventory.component.dashboard.SidePanel;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardPage extends javax.swing.JPanel implements ActionListener {

    private SidePanel sidePanel;
    private MainPage mainPage;

    public DashboardPage() {
        // setup
        this.setLayout(new MigLayout("insets 0"));

        this.sidePanel = new SidePanel();
        this.add(this.sidePanel, "height 100%, pos 0al 0 20% 100%");

        this.mainPage = new MainPage();
        this.add(this.mainPage, "height 100%, pos 20% 0 100% 100%");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
