package com.inventory.queries;

import com.inventory.main.DatabaseConnection;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductData {

    // CREATE
    public boolean insertProduct(String name, String category, int stock) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        boolean isSuccess = false;

        String query = "INSERT INTO product (product_name, category, stock) VALUES (?, ?, ?)";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, category);
            pstmt.setInt(3, stock);

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

    public void readAndSearchProducts(String keyword, DefaultTableModel model) {
        System.out.println("Displaying Table...");
    }

    // UPDATE
    public boolean updateStock(int productId, int quantityChange, boolean isIncoming) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        boolean isSuccess = false;

        String operator = isIncoming ? "+" : "-";
        String query = "UPDATE product SET stock = stock " + operator + " ? WHERE productID = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, quantityChange);
            pstmt.setInt(2, productId);

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
    public boolean deleteProduct(int productId) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        boolean isSuccess = false;

        String query = "DELETE FROM product WHERE productID = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, productId);

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

    // this is my own (simmon)
    public int getProductIDByName(String productName) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int productID = -1;
        String query = "SELECT productID FROM product WHERE product_name = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, productName);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                productID = rs.getInt("productID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productID;
    }
}