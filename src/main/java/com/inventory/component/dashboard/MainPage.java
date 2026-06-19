package com.inventory.component.dashboard;

import com.inventory.component.main_create.CreatePageContents;
import com.inventory.component.main_dashboard.DashboardPageContents;
import com.inventory.component.main_delete.DeletePageContents;
import com.inventory.component.main_register.RegisterUserPageContents;
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
    private String role;
    private RegisterUserPageContents registerUserPageContents;

    public MainPage(String role) {
        this.role = role;
        this.setLayout(new MigLayout("fill, insets 0, hidemode 3"));
        this.setBackground(new Color(0xd9d9d9));

        dashboardPageContents = new DashboardPageContents(role);
        this.add(dashboardPageContents, "cell 0 0, grow");

        createPageContents = new CreatePageContents();
        this.add(createPageContents, "cell 0 0, grow");

        updatePageContents = new UpdatePageContents(role);
        this.add(updatePageContents, "cell 0 0, grow");

        deletePageContents = new DeletePageContents(role);
        this.add(deletePageContents, "cell 0 0, grow");

        if (role.equals("Admin")) {
            registerUserPageContents = new RegisterUserPageContents();
            this.add(registerUserPageContents, "cell 0 0, grow");
        }

        hideAllPages();
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
            case "Register User" -> {
                if (role.equals("Admin") && registerUserPageContents != null && !registerUserPageContents.isVisible()) {
                    hideAllPages();
                    registerUserPageContents.setVisible(true);
                }
            }
        }
    }

    public void hideAllPages() {
        dashboardPageContents.setVisible(false);
        createPageContents.setVisible(false);
        updatePageContents.setVisible(false);
        deletePageContents.setVisible(false);
        if (registerUserPageContents != null) {
            registerUserPageContents.setVisible(false);
        }
    }

    public DashboardPageContents getDashboardPageContents() {
        return dashboardPageContents;
    }

    public DeletePageContents getDeletePageContents() {
        return deletePageContents;
    }

    public UpdatePageContents getUpdatePageContents() {
        return updatePageContents;
    }
}
