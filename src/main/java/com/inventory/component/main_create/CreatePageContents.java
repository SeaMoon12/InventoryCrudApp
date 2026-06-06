package com.inventory.component.main_create;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CreatePageContents extends JPanel {

    private JLabel titleLabel;
    private CreateCard createCard;

    public CreatePageContents() {
        this.setLayout(new MigLayout());
        titleLabel = new JLabel("Input new data");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        createCard = new CreateCard();

        this.add(titleLabel, "wrap");
        this.add(createCard, "width 100%, height 100%");
    }
}
