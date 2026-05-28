package sql_injection;

import java.sql.*;

public class VulnerableSearch {

    private String buildSearchQuery(String term) {
        String base = "SELECT name, price FROM products ";
        String condition = "WHERE name LIKE '%" + term + "%'";
        return base + condition;
    }

    public void searchProducts(Connection conn, String searchTerm) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(buildSearchQuery(searchTerm));
        while (rs.next()) {
            System.out.println(rs.getString("name") + " - " + rs.getString("price"));
        }
    }
}