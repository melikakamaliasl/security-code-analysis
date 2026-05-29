package sql_injection;

import java.sql.*;

// Fixed version of login using prepared statements
public class SafeLogin {

    public boolean login(Connection conn, String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }
}
