package com.inventory.component.main_update;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class UpdatePageContents extends JPanel {

    private JLabel titleLabel;
    private UpdateCard updateCard;

    public UpdatePageContents() {
        this.setLayout(new MigLayout());
        titleLabel = new JLabel("Update Existing Data");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        updateCard = new UpdateCard();

        this.add(titleLabel, "wrap");
        this.add(updateCard, "width 100%, height 100%");
    }
}
