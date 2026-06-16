package sql_injection;

import java.sql.*;

// Product search - TODO: switch to prepared statement
public class VulnerableSearch {

    public void searchProducts(Connection conn, String searchTerm) throws SQLException {
        String query = "SELECT name, price FROM products WHERE name LIKE '%" + searchTerm + "%'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            System.out.println(rs.getString("name") + " - " + rs.getString("price"));
        }
    }
}
