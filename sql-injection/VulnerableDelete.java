package sql_injection;

import java.sql.*;

// Delete user by ID - not safe, string concat in query
public class VulnerableDelete {

    public void deleteUser(Connection conn, String userId) throws SQLException {
        String query = "DELETE FROM users WHERE id = " + userId;
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        System.out.println("User deleted.");
    }
}
