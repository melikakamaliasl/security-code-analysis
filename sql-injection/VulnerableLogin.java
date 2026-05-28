package sql_injection;

import java.sql.*;

public class VulnerableLogin {

    private String buildQuery(String field1, String field2, String val1, String val2) {
        return String.format("SELECT * FROM %s WHERE %s = '%s' AND %s = '%s'",
                "users", field1, val1, field2, val2);
    }

    public boolean login(Connection conn, String username, String password) throws SQLException {
        String f1 = "user" + "name";
        String f2 = "pass" + "word";
        String query = buildQuery(f1, f2, username, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs.next();
    }
}