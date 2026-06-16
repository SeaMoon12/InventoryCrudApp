package com.inventory.component.dashboard;

import com.inventory.queries.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Header extends JPanel {

    private User loggedInUser;

    private JLabel usernameLabel;
    private JLabel profilePictureLabel;

    public Header(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.setLayout(new MigLayout("fill, insets 0 15 0 15", "[grow][]", "[grow]"));
        this.setBackground(Color.WHITE);

        initComponents();
        addComponents();
    }

    private void initComponents() {
        String username = loggedInUser != null ? loggedInUser.getUsername() : "User";
        usernameLabel = new JLabel(username);
        usernameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        usernameLabel.setForeground(new Color(0x3a3a3a));

        profilePictureLabel = new JLabel();
        profilePictureLabel.setPreferredSize(new Dimension(42, 42));
        profilePictureLabel.setHorizontalAlignment(SwingConstants.CENTER);

        byte[] imageBytes = loggedInUser != null ? loggedInUser.getProfilePicture() : null;

        if (imageBytes != null && imageBytes.length > 0) {
            ImageIcon rawIcon = new ImageIcon(imageBytes);
            profilePictureLabel.setIcon(makeCircularIcon(rawIcon, 42));
        } else {
            profilePictureLabel.setIcon(makeInitialsIcon(username, 42));
        }
    }

    private void addComponents() {
        this.add(new JLabel(), "grow");
        this.add(usernameLabel, "gapright 8, ay center");
        this.add(profilePictureLabel, "width 42!, height 42!, ay center");
    }

    private ImageIcon makeCircularIcon(ImageIcon source, int size) {
        BufferedImage output = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(new Ellipse2D.Float(0, 0, size, size));

        Image scaled = source.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        g2.drawImage(scaled, 0, 0, null);
        g2.dispose();

        return new ImageIcon(output);
    }

    private ImageIcon makeInitialsIcon(String username, int size) {
        BufferedImage output = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(0x795fbe));
        g2.fillOval(0, 0, size, size);

        String initial = username != null && !username.isEmpty()
                ? String.valueOf(username.charAt(0)).toUpperCase()
                : "?";

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, size / 2));
        FontMetrics fm = g2.getFontMetrics();
        int x = (size - fm.stringWidth(initial)) / 2;
        int y = (size - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(initial, x, y);
        g2.dispose();

        return new ImageIcon(output);
    }
}