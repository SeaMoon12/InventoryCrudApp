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

    // ===== this is my own (simmon) =====
    public Product getProductByID(int productID) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM product WHERE productID = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, productID);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("productID"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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

    public int getCurrentStock(int productID) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int currentStock = -1;
        String query = "SELECT stock FROM product WHERE productID = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, productID);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                currentStock = rs.getInt("stock");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentStock;
    }

    // for typos for new products. for example iPhone 20 is a new product i wanna add but i did a typo
    public boolean updateProductDetails(int productID, String newName, String newCategory) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        boolean isSuccess = false;

        String query = "UPDATE product SET product_name = ?, category = ? WHERE productID = ?";

        try {
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, newName);
            pstmt.setString(2, newCategory);
            pstmt.setInt(3, productID);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    // for typos for existing products. for example iPhone 15 is already in stock, but when i wanted to
    // add a new transaction to increase/decrease the stock, i did a typo
    public boolean mergeProductStock(int targetProductID, int sourceProductID) {
        int stockToMove = getCurrentStock(sourceProductID);

        if (stockToMove <= 0) {
            return true;
        }
        return updateStock(targetProductID, stockToMove, true);
    }
}