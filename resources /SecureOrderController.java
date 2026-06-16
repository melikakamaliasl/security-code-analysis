package resources;

import java.sql.*;

public class SecureOrderController {
    private Connection conn;

    public SecureOrderController(Connection conn) {
        this.conn = conn;
    }

    public String getOrder(String orderId, String currentUser) throws SQLException {
        String query = "SELECT * FROM orders WHERE id = ? AND owner = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, orderId);
        stmt.setString(2, currentUser);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getString("details");
        }
        throw new SecurityException("Access denied or order not found");
    }
}