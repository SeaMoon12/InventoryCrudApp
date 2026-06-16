package com.inventory.component.login;

import com.inventory.component.CustomButton;
import com.inventory.component.CustomTextField;
import com.inventory.main.DatabaseConnection;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterCard extends JPanel implements ActionListener {

    private ActionListener actionListener;

    private String[] roleOptions = {"Viewer", "Operator", "Manager"};
    private CustomTextField usernameField;
    private LoginPasswordField passwordField;
    private JComboBox<String> roleCombo;
    private CustomButton chooseImageButton;
    private JLabel imagePreviewLabel;
    private CustomButton registerButton;

    private byte[] selectedImageBytes = null;

    public RegisterCard() {
        this.setLayout(new MigLayout("fillx, insets 10, gapy 3"));
        this.setOpaque(false);

        initComponents();
        addComponents();

        chooseImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onChooseImageClick();
            }
        });

        registerButton.addActionListener(this);
    }

    private void initComponents() {
        usernameField = new CustomTextField(Color.WHITE);
        usernameField.setPreferredSize(new Dimension(200, 30));
        usernameField.setPlaceholder("Enter username...");

        passwordField = new LoginPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.setPlaceholder("Enter password...");

        roleCombo = new JComboBox<>(roleOptions);

        chooseImageButton = new CustomButton("Choose Profile Picture");

        imagePreviewLabel = new JLabel("No image selected");
        imagePreviewLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        imagePreviewLabel.setForeground(Color.DARK_GRAY);

        registerButton = new CustomButton("Register");
    }

    private void addComponents() {
        this.add(new JLabel("Username"), "wrap");
        this.add(usernameField, "grow, wrap");

        this.add(new JLabel("Password"), "wrap");
        this.add(passwordField, "grow, wrap");

        this.add(new JLabel("Role"), "wrap");
        this.add(roleCombo, "grow, wrap");

        this.add(chooseImageButton, "grow, wrap");
        this.add(imagePreviewLabel, "wrap");

        this.add(registerButton, "center, gapy 15");
    }

    private void onChooseImageClick() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Image Files", "jpg", "jpeg", "png", "gif", "bmp"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedImageBytes = readFileToBytes(selectedFile);
            if (selectedImageBytes != null) {
                imagePreviewLabel.setText(selectedFile.getName());
            }
        }
    }

    private byte[] readFileToBytes(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.out.println("Failed to read image file: " + e.getMessage());
            return null;
        }
    }

    public boolean insertUser(String username, String password, String role, byte[] profilePicture) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO Users (username, password, role, profile_picture) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            if (profilePicture != null) {
                stmt.setBytes(4, profilePicture);
            } else {
                stmt.setNull(4, java.sql.Types.BLOB);
            }

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("User insertion failed: " + e.getMessage());
            return false;
        }
    }

    public void addEvent(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public String getUsername() { return usernameField.getText(); }
    public String getPassword() { return passwordField.getText(); }
    public String getRole()     { return (String) roleCombo.getSelectedItem(); }
    public byte[] getSelectedImageBytes() { return selectedImageBytes; }

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        roleCombo.setSelectedIndex(0);
        imagePreviewLabel.setText("No image selected");
        selectedImageBytes = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton && actionListener != null) {
            actionListener.actionPerformed(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        g2D.setColor(new Color(0xd9d9d9));
        g2D.fillRoundRect(0, 0, width, height, 10, 10);
    }
}