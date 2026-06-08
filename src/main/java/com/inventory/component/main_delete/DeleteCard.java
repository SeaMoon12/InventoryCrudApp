package com.inventory.component.main_delete;

import com.inventory.component.CustomTextField;
import com.inventory.component.dashboard.ShadowCard;
import com.inventory.queries.ProductData;
import com.inventory.queries.TransactionData;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class DeleteCard extends ShadowCard {

    private ProductData pData = new ProductData();
    private TransactionData tData = new TransactionData();

    private JLabel whatToDelete;
    private String[] options = {"Data", "Transaction"};
    private JComboBox<String> dropdown;

    private JLabel dropdownSelectionLabel;
    private CustomTextField dropdownSelectionText;

    private JButton deleteButton;

    public DeleteCard() {
        initComponents();
        addComponents();

        dropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) dropdown.getSelectedItem();
                onDropdownSelected(selected);
            }
        });

        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonClick();
            }
        });
    }

    private void initComponents() {
        this.setLayout(new MigLayout("insets 25"));
        whatToDelete = new JLabel("What to delete?");
        whatToDelete.setFont(new Font("Arial", Font.BOLD, 18));
        dropdown = new JComboBox<>(options);
        dropdown.setFont(new Font("Arial", Font.BOLD, 14));

        dropdownSelectionLabel = new JLabel("Product ID");
        dropdownSelectionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dropdownSelectionText = new CustomTextField(new Color(0xd9d9d9), "Enter product ID...");

        deleteButton = new JButton("Delete");
    }

    private void addComponents() {
        this.add(whatToDelete, "wrap");
        this.add(dropdown, "wrap");

        // Section 2
        this.add(dropdownSelectionLabel, "wrap");
        this.add(dropdownSelectionText, "width 100%, wrap");

        this.add(deleteButton, "align right");
    }

    private void onDropdownSelected(String selected) {
        //selectedLabel.setText(selected);
        if (selected.equals("Data")) {
            dropdownSelectionLabel.setText("Product ID");
            dropdownSelectionText.setPlaceholder("Enter product ID...");
        } else if (selected.equals("Transaction")) {
            dropdownSelectionLabel.setText("Transaction ID");
            dropdownSelectionText.setPlaceholder("Enter transaction ID...");
        }
    }

    private void onButtonClick() {
        boolean deleteSuccessful;

        if (dropdownSelectionLabel.getText().equals("Product ID")) {
            try {
                deleteSuccessful = pData.deleteProduct(Integer.parseInt(dropdownSelectionText.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a number in the Product ID field.",  "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            try {
                deleteSuccessful = tData.deleteTransaction(Integer.parseInt(dropdownSelectionText.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a number in the Transaction ID field.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (deleteSuccessful) {
            JOptionPane.showMessageDialog(this, dropdownSelectionLabel.getText() + " deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, dropdownSelectionLabel.getText() + " could not be deleted.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
