package identity;

import java.sql.*;

public class RegistrationService {
    private Connection conn;

    public RegistrationService(Connection conn) {
        this.conn = conn;
    }

    public void register(String username, String password) throws SQLException {
        String query = "INSERT INTO users (username, password) VALUES ('"
                + username + "', '" + password + "')";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
    }
}