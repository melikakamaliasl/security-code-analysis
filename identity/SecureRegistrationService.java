package identity;

import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class SecureRegistrationService {
    private Connection conn;

    public SecureRegistrationService(Connection conn) {
        this.conn = conn;
    }

    public void register(String username, String password) throws SQLException {
        String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        String query = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, hash);
        stmt.executeUpdate();
    }
}