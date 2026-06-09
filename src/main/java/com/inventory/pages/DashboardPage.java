package com.inventory.pages;

import com.inventory.component.dashboard.Header;
import com.inventory.component.dashboard.MainPage;
import com.inventory.component.dashboard.PageNavigatorListener;
import com.inventory.component.dashboard.SidePanel;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardPage extends javax.swing.JPanel {

    private LogoutButtonListener logoutButtonListener;
    private SidePanel sidePanel;
    private Header header;
    private MainPage mainPage;

    public DashboardPage() {
        initMainComponents();
    }

    private void initMainComponents() {
        // setup
        this.setLayout(new MigLayout("insets 0"));

        this.sidePanel = new SidePanel();
        this.add(this.sidePanel, "height 100%, pos 0al 0 20% 100%");

        this.header = new Header();
        this.add(this.header, "height 10%, pos 20% 0 100% 10%");

        this.mainPage = new MainPage();
        this.add(this.mainPage, "height 90%, pos 20% 10% 100% 100%");

        sidePanel.setNavListener(new PageNavigatorListener() {
            @Override
            public void onNavigate(String pageName) {
                if (pageName.equals("Dashboard")) {
                    // refresh table, because user cannot see table until user clicks the dashboard Sidebar button
                    mainPage.getDashboardPageContents().getStockHistory().refreshTableData();
                } else if (pageName.equals("Logout")) {
                    logoutButtonListener.onLogout();
                    return;
                }
                mainPage.showPage(pageName);
            }
        });
    }

    public MainPage getMainPagePanel() {
        return this.mainPage;
    }

    public void setLogoutButtonListener(LogoutButtonListener logoutButtonListener) {
        this.logoutButtonListener = logoutButtonListener;
    }
}
