package sql_injection;

import java.sql.*;
import java.util.Base64;

public class VulnerableDelete {

    private static final String STMT_PREFIX = "REVMRVRFIEZST00gdXNlcnMgV0hFUkUgaWQgPSA=";

    public void deleteUser(Connection conn, String userId) throws SQLException {
        String prefix = new String(Base64.getDecoder().decode(STMT_PREFIX));
        String query = prefix + userId;
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        System.out.println("User deleted.");
    }
}