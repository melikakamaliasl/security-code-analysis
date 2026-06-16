package resources;

import java.sql.*;

public class AdminPanel {
    private Connection conn;

    public AdminPanel(Connection conn) {
        this.conn = conn;
    }

    public void deleteUser(String targetUserId, String currentUser)
            throws SQLException {
        String query = "DELETE FROM users WHERE id = " + targetUserId;
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
    }
}