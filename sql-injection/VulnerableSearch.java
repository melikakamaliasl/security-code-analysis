package sql_injection;

import java.sql.*;
import java.util.Base64;

public class VulnerableSearch {

    private static final String QUERY_BASE = "U0VMRUNUIG5hbWUsIHByaWNlIEZST00gcHJvZHVjdHMgV0hFUkUgbmFtZSBMSUtFICclJXMlJw==";

    public void searchProducts(Connection conn, String searchTerm) throws SQLException {
        String template = new String(Base64.getDecoder().decode(QUERY_BASE));
        String query = String.format(template, searchTerm);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            System.out.println(rs.getString("name") + " - " + rs.getString("price"));
        }
    }
}