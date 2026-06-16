package identity;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;

public class SecureLoginService {
    private Connection conn;
    private Map<String, Integer> failedAttempts = new HashMap<>();
    private static final int MAX_ATTEMPTS = 5;

    public SecureLoginService(Connection conn) {
        this.conn = conn;
    }

    public boolean authenticate(String username, String password)
            throws SQLException {
        int attempts = failedAttempts.getOrDefault(username, 0);
        if (attempts >= MAX_ATTEMPTS) {
            throw new SecurityException("Account locked — too many failed attempts");
        }
        String query = "SELECT password_hash FROM users WHERE username = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String hash = rs.getString("password_hash");
            if (BCrypt.checkpw(password, hash)) {
                failedAttempts.remove(username);
                return true;
            }
        }
        failedAttempts.put(username, attempts + 1);
        return false;
    }
}