package resources;

import java.sql.*;

public class OrderController {
    private Connection conn;

    public OrderController(Connection conn) {
        this.conn = conn;
    }

    public String getOrder(String orderId, String currentUser) throws SQLException {
        String query = "SELECT * FROM orders WHERE id = " + orderId;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            return rs.getString("details");
        }
        return null;
    }
}