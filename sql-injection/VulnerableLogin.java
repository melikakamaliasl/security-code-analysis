package sql_injection;

import java.sql.*;
import java.util.Base64;

public class VulnerableLogin {

    // Base64 encoded: "SELECT * FROM users WHERE username = '%s' AND password = '%s'"
    private static final String QUERY_TEMPLATE = "U0VMRUNUICogRlJPTSB1c2VycyBXSEVSRSB1c2VybmFtZSA9ICclcycgQU5EIHBhc3N3b3JkID0gJyVzJw==";

    public boolean login(Connection conn, String username, String password) throws SQLException {
        String template = new String(Base64.getDecoder().decode(QUERY_TEMPLATE));
        String query = String.format(template, username, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs.next();
    }
}