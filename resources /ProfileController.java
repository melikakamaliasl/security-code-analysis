package resources;

import java.sql.*;

public class ProfileController {
    private Connection conn;

    public ProfileController(Connection conn) {
        this.conn = conn;
    }

    public void updateProfile(String userId, String newEmail, String currentUser)
            throws SQLException {
        String query = "UPDATE users SET email = '" + newEmail +
                "' WHERE id = " + userId;
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
    }
}