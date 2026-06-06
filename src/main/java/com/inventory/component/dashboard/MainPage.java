package com.inventory.component.dashboard;

import com.inventory.component.main_create.CreatePageContents;
import com.inventory.component.main_dashboard.DashboardPageContents;
import com.inventory.component.main_delete.DeletePageContents;
import com.inventory.component.main_update.UpdatePageContents;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// This is where all the components will be added to the main page, such as the dashboard, products, etc.
public class MainPage extends JPanel {

    private DashboardPageContents dashboardPageContents;
    private CreatePageContents createPageContents;
    private UpdatePageContents updatePageContents;
    private DeletePageContents deletePageContents;

    public MainPage() {
        this.setLayout(new MigLayout("fill, insets 0, hidemode 3"));
        this.setBackground(new Color(0xd9d9d9));

        dashboardPageContents = new DashboardPageContents();
        this.add(dashboardPageContents, "cell 0 0, grow");

        createPageContents = new CreatePageContents();
        this.add(createPageContents, "cell 0 0, grow");

        updatePageContents = new UpdatePageContents();
        this.add(updatePageContents, "cell 0 0, grow");

        deletePageContents = new DeletePageContents();
        this.add(deletePageContents, "cell 0 0, grow");

        hideAllPages();
        // First view when user first opens the dashboard
        dashboardPageContents.setVisible(true);
    }

    public void showPage(String pageName) {
        switch (pageName) {
            case "Dashboard" -> {
                if (!dashboardPageContents.isVisible()) {
                    hideAllPages();
                    dashboardPageContents.setVisible(true);
                }
            }
            case "Create" -> {
                if (!createPageContents.isVisible()) {
                    hideAllPages();
                    createPageContents.setVisible(true);
                }
            }
            case "Update" -> {
                if (!updatePageContents.isVisible()) {
                    hideAllPages();
                    updatePageContents.setVisible(true);
                }
            }
            case "Delete" -> {
                if (!deletePageContents.isVisible()) {
                    hideAllPages();
                    deletePageContents.setVisible(true);
                }
            }
            case "Logout" -> System.out.println("Logout Button Clicked");
        }
    }

    public void hideAllPages() {
        dashboardPageContents.setVisible(false);
        createPageContents.setVisible(false);
        updatePageContents.setVisible(false);
        deletePageContents.setVisible(false);
    }

    public DashboardPageContents getDashboardPageContents() {
        return dashboardPageContents;
    }
}
