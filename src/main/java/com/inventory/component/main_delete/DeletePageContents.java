package com.inventory.component.main_delete;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DeletePageContents extends JPanel {

    private JLabel titleLabel;
    private DeleteCard deleteCard;

    public DeletePageContents() {
        this.setLayout(new MigLayout());
        titleLabel = new JLabel("Delete data");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        deleteCard = new DeleteCard();

        this.add(titleLabel, "wrap");
        this.add(deleteCard, "width 100%, height 100%");
    }

    public DeleteCard getDeleteCard() {
        return deleteCard;
    }
}
