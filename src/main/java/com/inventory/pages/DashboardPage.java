package com.inventory.pages;

import com.inventory.component.dashboard.Header;
import com.inventory.component.dashboard.MainPage;
import com.inventory.component.dashboard.PageNavigatorListener;
import com.inventory.component.dashboard.SidePanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardPage extends javax.swing.JPanel implements ActionListener {

    private SidePanel sidePanel;
    private Header header;
    private MainPage mainPage;

    private DefaultTableModel productsTableModel;
    private DefaultTableModel transactionsTableModel;

    public DashboardPage() {
        initTables();
        initMainComponents();
    }

    private void initMainComponents() {
        // setup
        this.setLayout(new MigLayout("insets 0"));

        this.sidePanel = new SidePanel();
        this.add(this.sidePanel, "height 100%, pos 0al 0 20% 100%");

        this.header = new Header();
        this.add(this.header, "height 10%, pos 20% 0 100% 10%");

        this.mainPage = new MainPage(productsTableModel, transactionsTableModel);
        this.add(this.mainPage, "height 90%, pos 20% 10% 100% 100%");

        sidePanel.setNavListener(new PageNavigatorListener() {
            @Override
            public void onNavigate(String pageName) {
                mainPage.showPage(pageName);
            }
        });
    }

    private void initTables() {
        String[] tempProductColumns = {"productID", "product_name", "category", "stock"};
        Object[][] tempProductData = {
                {"1", "iPhone 15 Pro Max 256GB", "Smartphones", "15"},
                {"2", "Samsung Galaxy S24 Ultra", "Smartphones", "10"},
                {"3", "Logitech MX Master 3S Wireless Mouse", "Accessories", "45"},
                {"4", "Anker PowerBank 20,000mAh", "Accessories", "60"},
                {"5", "Keychron K2 Mechanical Keyboard", "Accessories", "22"}
        };

        String[] tempTransactionColumns = {"transaction_id", "productID", "quantity", "transaction_type", "transaction_date"};
        Object[][] tempTransactionData = {
                {"1", "1", "20", "Incoming", "2026-6-6"},
                {"2", "1", "5", "Outgoing", "2026-5-3"},
                {"3", "2", "10", "Incoming", "2026-3-5"},
                {"4", "3", "50", "Incoming", "2026-4-23"},
                {"5", "3", "5", "Outgoing", "2026-8-16"},
                {"6", "4", "60", "Incoming", "2026-2-4"},
                {"7", "5", "25", "Incoming", "2026-11-16"},
                {"8", "5", "3", "Outgoing", "2026-7-14"}
        };

        productsTableModel = new DefaultTableModel(tempProductData, tempProductColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        transactionsTableModel = new DefaultTableModel(tempTransactionData, tempTransactionColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
