package sql_injection;

import java.sql.*;

public class VulnerableDelete {

    private String getDeleteStatement(String id) {
        String[] parts = {"DELETE FROM users WHERE id = ", id};
        return String.join("", parts);
    }

    public void deleteUser(Connection conn, String userId) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(getDeleteStatement(userId));
        System.out.println("User deleted.");
    }
}