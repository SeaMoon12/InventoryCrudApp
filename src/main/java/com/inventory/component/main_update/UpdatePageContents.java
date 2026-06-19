package com.inventory.component.main_update;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UpdatePageContents extends JPanel {

    private JLabel titleLabel;
    private UpdateCard updateCard;
    private String role;

    public UpdatePageContents(String role) {
        this.role = role;
        this.setLayout(new MigLayout());
        titleLabel = new JLabel("Update Existing Data");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        updateCard = new UpdateCard(role);

        this.add(titleLabel, "wrap");
        this.add(updateCard, "width 100%, height 100%");
    }

    public UpdateCard getUpdateCard() {
        return updateCard;
    }
}
