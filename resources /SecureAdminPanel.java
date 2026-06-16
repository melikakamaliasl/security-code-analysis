package resources;

import java.sql.*;

public class SecureAdminPanel {
    private Connection conn;

    public SecureAdminPanel(Connection conn) {
        this.conn = conn;
    }

    public void deleteUser(String targetUserId, String currentUser)
            throws SQLException {
        String roleQuery = "SELECT role FROM users WHERE username = ?";
        PreparedStatement roleStmt = conn.prepareStatement(roleQuery);
        roleStmt.setString(1, currentUser);
        ResultSet rs = roleStmt.executeQuery();
        if (!rs.next() || !rs.getString("role").equals("admin")) {
            throw new SecurityException("Admin privileges required");
        }
        String deleteQuery = "DELETE FROM users WHERE id = ?";
        PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
        deleteStmt.setString(1, targetUserId);
        deleteStmt.executeUpdate();
    }
}