package com.inventory.queries;

import com.inventory.main.DatabaseConnection;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class UserData {

    // CREATE
    public boolean insertUser(String username, String password, String role, byte[] profilePicture) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        boolean isSuccess = false;

        String query = "INSERT INTO Users (username, password, role, profile_picture) VALUES (?, ?, ?, ?)";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            if (profilePicture != null) {
                pstmt.setBytes(4, profilePicture);
            } else {
                pstmt.setNull(4, Types.BLOB);
            }

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    // READ (for dashboard table — Admin only)
    public void readAllUsers(DefaultTableModel model) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        model.setRowCount(0);

        String query = "SELECT userID, username, role FROM Users";

        try {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userID");
                String username = rs.getString("username");
                String role = rs.getString("role");
                model.addRow(new Object[]{id, username, role});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // UPDATE
    public boolean updateUser(int userID, String newUsername, String newRole, String newPassword, byte[] newProfilePicture) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        boolean isSuccess = false;

        boolean updatePassword = newPassword != null && !newPassword.isEmpty();
        boolean updatePicture = newProfilePicture != null;

        String query;
        if (updatePassword && updatePicture) {
            query = "UPDATE Users SET username = ?, role = ?, password = ?, profile_picture = ? WHERE userID = ?";
        } else if (updatePassword) {
            query = "UPDATE Users SET username = ?, role = ?, password = ? WHERE userID = ?";
        } else if (updatePicture) {
            query = "UPDATE Users SET username = ?, role = ?, profile_picture = ? WHERE userID = ?";
        } else {
            query = "UPDATE Users SET username = ?, role = ? WHERE userID = ?";
        }

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newUsername);
            pstmt.setString(2, newRole);

            int paramIndex = 3;
            if (updatePassword) {
                pstmt.setString(paramIndex++, newPassword);
            }
            if (updatePicture) {
                pstmt.setBytes(paramIndex++, newProfilePicture);
            }
            pstmt.setInt(paramIndex, userID);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    // DELETE
    public boolean deleteUser(int userID) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        boolean isSuccess = false;

        String query = "DELETE FROM Users WHERE userID = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userID);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    public User getUserByID(int userID) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Users WHERE userID = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("userID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getBytes("profile_picture")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // Used by authenticate (LoginPage equivalent)
    public User getUserByCredentials(String username, String password) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("userID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getBytes("profile_picture")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String[] getUserIDArray() {
        ArrayList<String> userIDArray = new ArrayList<String>();
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        String query = "SELECT userID FROM Users";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                userIDArray.add(rs.getString("userID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userIDArray.toArray(new String[userIDArray.size()]);
    }
}