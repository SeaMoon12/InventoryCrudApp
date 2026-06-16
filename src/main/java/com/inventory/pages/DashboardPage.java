package com.inventory.pages;

import com.inventory.component.dashboard.Header;
import com.inventory.component.dashboard.MainPage;
import com.inventory.component.dashboard.PageNavigatorListener;
import com.inventory.component.dashboard.SidePanel;
import com.inventory.component.main_dashboard.DashboardPageContents;
import com.inventory.queries.ProductData;
import com.inventory.queries.TransactionData;
import com.inventory.queries.UserData;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class DashboardPage extends javax.swing.JPanel {

    private ProductData pData = new ProductData();
    private TransactionData tData = new TransactionData();
    private LogoutButtonListener logoutButtonListener;
    private SidePanel sidePanel;
    private Header header;
    private MainPage mainPage;
    private String role;

    public DashboardPage(String role) {
        this.role = role;
        initMainComponents();
    }

    private void initMainComponents() {
        // setup
        this.setLayout(new MigLayout("insets 0"));

        this.sidePanel = new SidePanel(role);
        this.add(this.sidePanel, "height 100%, pos 0al 0 20% 100%");

        this.header = new Header();
        this.add(this.header, "height 10%, pos 20% 0 100% 10%");

        this.mainPage = new MainPage(role);
        this.add(this.mainPage, "height 90%, pos 20% 10% 100% 100%");

        sidePanel.setNavListener(new PageNavigatorListener() {
            @Override
            public void onNavigate(String pageName) {
                UserData uData = new UserData();

                mainPage.getDeletePageContents().getDeleteCard().setProductIDOptions(pData.getProductIDArray());
                mainPage.getDeletePageContents().getDeleteCard().setTransactionIDOptions(tData.getTransactionIDArray());
                mainPage.getUpdatePageContents().getUpdateCard().setProductIDOptions(pData.getProductIDArray());
                mainPage.getUpdatePageContents().getUpdateCard().setTransactionIDOptions(tData.getTransactionIDArray());

                if (role.equals("Admin")) {
                    mainPage.getDeletePageContents().getDeleteCard().setUserIDOptions(uData.getUserIDArray());
                    mainPage.getUpdatePageContents().getUpdateCard().setUserIDOptions(uData.getUserIDArray());
                    mainPage.getDashboardPageContents().getStockHistory().setUserIDOptions(uData.getUserIDArray());
                }

                if (pageName.equals("Dashboard")) {
                    // refresh table, because user cannot see table until user clicks the dashboard Sidebar button
                    DashboardPageContents dashboardPageContents = mainPage.getDashboardPageContents();
                    dashboardPageContents.getStockHistory().refreshTableData();
                    dashboardPageContents.getAmountOFLowStockCard().refresh();
                    dashboardPageContents.getAmountOfOutOfStockCard().refresh();
                    dashboardPageContents.getAmountOfProductsCard().refresh();
                    dashboardPageContents.getStock().refresh();
                } else if (pageName.equals("Logout")) {
                    logoutButtonListener.onLogout();
                    return;
                } else if (pageName.equals("Register User")) {
                    // No pre-load needed for register; just navigate
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
