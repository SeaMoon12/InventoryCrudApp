package com.inventory.queries;

import com.inventory.main.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class TransactionData {

    // CREATE
    public boolean insertTransaction(int productId, int quantity, String type) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        boolean isSuccess = false;

        String query = "INSERT INTO transaction (productID, quantity, transaction_type) VALUES (?, ?, ?)";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, productId);
            pstmt.setInt(2, quantity);
            pstmt.setString(3, type);

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

    // READ & SEARCH
    public void readAndSearchTransactions(String keyword, DefaultTableModel model) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        model.setRowCount(0);

        String query = "SELECT t.transaction_id, p.product_name, t.quantity, t.transaction_type, t.transaction_date " +
                "FROM transaction t " +
                "INNER JOIN product p ON t.productID = p.productID " +
                "WHERE p.product_name LIKE ?";

        try {
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, "%" + keyword + "%");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("transaction_id");
                String name = rs.getString("product_name");
                int qty = rs.getInt("quantity");
                String type = rs.getString("transaction_type");
                String date = rs.getString("transaction_date");

                model.addRow(new Object[]{id, name, qty, type, date});
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
    public boolean updateTransaction(int transactionId, int newQuantity, String newType) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        boolean isSuccess = false;

        String query = "UPDATE transaction SET quantity = ?, transaction_type = ? WHERE transaction_id = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, newQuantity);
            pstmt.setString(2, newType);
            pstmt.setInt(3, transactionId);

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
    public boolean deleteTransaction(int transactionId) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        boolean isSuccess = false;

        String query = "DELETE FROM transaction WHERE transaction_id = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, transactionId);

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
    public Transaction getTransactionByID(int transactionID) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM transaction WHERE transaction_id = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, transactionID);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Transaction(
                        rs.getInt("transaction_id"),
                        rs.getInt("productID"),
                        rs.getInt("quantity"),
                        rs.getString("transaction_type"),
                        rs.getString("transaction_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // after merging a typo product that already exists
    public boolean changeTransactionProductID(int targetProductID, int sourceProductID) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;

        String query = "UPDATE transaction SET productID = ? WHERE productID = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, targetProductID);
            pstmt.setInt(2, sourceProductID);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getTransactionIDByProductID(int productID) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT transaction_id FROM transaction WHERE productID = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, productID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("transaction_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean deleteAllTransactionsByProductID(int productID) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        boolean isSuccess = false;

        String query = "DELETE FROM transaction WHERE productID = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, productID);

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
}