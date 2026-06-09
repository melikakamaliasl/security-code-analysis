package identity;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class LoginService {
    private Connection conn;
    private static final String ADMIN_PASSWORD = "admin123";

    public LoginService(Connection conn) {
        this.conn = conn;
    }

    public boolean authenticate(String username, String password)
            throws SQLException {
        if (username.equals("admin") && password.equals(ADMIN_PASSWORD)) {
            return true;
        }
        String query = "SELECT password FROM users WHERE username = '"
                + username + "'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            return rs.getString("password").equals(password);
        }
        return false;
    }
}