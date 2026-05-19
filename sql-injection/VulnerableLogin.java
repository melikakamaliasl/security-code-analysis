package sql_injection;

import java.sql.*;

// Basic login - needs fixing, vulnerable to SQL injection
public class VulnerableLogin {

    public boolean login(Connection conn, String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = '" + username
                     + "' AND password = '" + password + "'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs.next();
    }
}
