package com.inventory.component.main_delete;

import com.inventory.component.CustomTextField;
import com.inventory.component.dashboard.ShadowCard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteCard extends ShadowCard {

    private JLabel whatToDelete;
    private String[] options = {"Data", "Transaction"};
    private JComboBox<String> dropdown;
    private JLabel selectedLabel = new JLabel();

    private JLabel dropdownSelectionLabel;
    private CustomTextField dropdownSelectionText;

    private JButton deleteButton;

    public DeleteCard() {
        this.setLayout(new MigLayout("insets 15"));
        whatToDelete = new JLabel("What to delete?");
        whatToDelete.setFont(new Font("Arial", Font.BOLD, 18));
        dropdown = new JComboBox<>(options);
        dropdown.setFont(new Font("Arial", Font.BOLD, 14));

        dropdownSelectionLabel = new JLabel("Product ID");
        dropdownSelectionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        dropdownSelectionText = new CustomTextField(new Color(0xd9d9d9), "Enter product ID...");

        deleteButton = new JButton("Delete");

        dropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) dropdown.getSelectedItem();
                onDropdownSelected(selected);
            }
        });

        this.add(whatToDelete, "wrap");
        this.add(dropdown, "wrap");

        // Section 2
        this.add(dropdownSelectionLabel, "wrap");
        this.add(dropdownSelectionText, "width 100%, wrap");

        this.add(deleteButton, "align right");
    }

    private void onDropdownSelected(String selected) {
        selectedLabel.setText(selected);
        if (selected.equals("Data")) {
            dropdownSelectionLabel.setText("Product ID");
            dropdownSelectionText.setPlaceholder("Enter product ID...");
        } else if (selected.equals("Transaction")) {
            dropdownSelectionLabel.setText("Transaction ID");
            dropdownSelectionText.setPlaceholder("Enter transaction ID...");
        }
    }
}
