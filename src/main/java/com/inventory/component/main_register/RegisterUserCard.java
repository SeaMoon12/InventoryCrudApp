package com.inventory.component.main_register;

import com.inventory.component.CustomButton;
import com.inventory.component.CustomTextField;
import com.inventory.component.dashboard.ShadowCard;
import com.inventory.queries.UserData;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class RegisterUserCard extends ShadowCard {

    private UserData uData = new UserData();

    private JLabel usernameLabel;
    private CustomTextField usernameTextField;

    private JLabel passwordLabel;
    private CustomTextField passwordTextField;

    private String[] roleOptions = {"Admin", "Operator", "Viewer"};
    private JLabel roleLabel;
    private JComboBox<String> roleCombo;

    private JLabel profilePictureLabel;
    private JLabel profilePicturePreview;
    private CustomButton choosePictureButton;
    private byte[] selectedImageBytes;

    private CustomButton registerButton;

    public RegisterUserCard() {
        this.setLayout(new MigLayout("insets 25"));
        initComponents();
        addComponents();
    }

    private void initComponents() {
        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameTextField = new CustomTextField(new Color(0xd9d9d9), "Enter username...");

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordTextField = new CustomTextField(new Color(0xd9d9d9), "Enter password...");

        roleLabel = new JLabel("Role");
        roleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        roleCombo = new JComboBox<>(roleOptions);

        profilePictureLabel = new JLabel("Profile Picture");
        profilePictureLabel.setFont(new Font("Arial", Font.BOLD, 18));

        profilePicturePreview = new JLabel("No image selected");
        profilePicturePreview.setPreferredSize(new Dimension(80, 80));
        profilePicturePreview.setHorizontalAlignment(SwingConstants.CENTER);
        profilePicturePreview.setOpaque(true);
        profilePicturePreview.setBackground(new Color(0xd9d9d9));

        choosePictureButton = new CustomButton("Choose Image");
        choosePictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onChoosePictureClicked();
            }
        });

        registerButton = new CustomButton("Register User");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRegisterClicked();
            }
        });
    }

    private void addComponents() {
        this.add(usernameLabel, "wrap");
        this.add(usernameTextField, "width 90%, wrap");

        this.add(passwordLabel, "wrap, gapy 5");
        this.add(passwordTextField, "width 90%, wrap");

        this.add(roleLabel, "wrap, gapy 5");
        this.add(roleCombo, "width 90%, wrap");

        this.add(profilePictureLabel, "wrap, gapy 5");
        this.add(profilePicturePreview, "wrap");
        this.add(choosePictureButton, "wrap, gapy 3");

        this.add(registerButton, "align right");
    }

    private void onChoosePictureClicked() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Profile Picture");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Image files", "jpg", "jpeg", "png", "gif", "bmp"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedImageBytes = readImageToBytes(selectedFile);

            if (selectedImageBytes != null) {
                try {
                    Image img = ImageIO.read(selectedFile);
                    Image scaledImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    profilePicturePreview.setIcon(new ImageIcon(scaledImg));
                    profilePicturePreview.setText("");
                } catch (IOException e) {
                    profilePicturePreview.setText("Preview failed");
                }
            }
        }
    }

    private byte[] readImageToBytes(File file) {
        byte[] imageBytes = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            imageBytes = new byte[(int) file.length()];
            fis.read(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to read image file.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imageBytes;
    }

    private void onRegisterClicked() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String role = (String) roleCombo.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in username and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isSuccessful = uData.insertUser(username, password, role, selectedImageBytes);

        if (isSuccessful) {
            JOptionPane.showMessageDialog(null, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(null, "Registration failed. Username may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        usernameTextField.setText("");
        passwordTextField.setText("");
        roleCombo.setSelectedIndex(0);
        profilePicturePreview.setIcon(null);
        profilePicturePreview.setText("No image selected");
        selectedImageBytes = null;
    }
}