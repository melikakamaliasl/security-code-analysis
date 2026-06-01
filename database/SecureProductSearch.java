package datbase;

import java.sql.*;

// Fixed product search
public class SecureProductSearch {

    public void searchProducts(Connection conn, String searchTerm) throws SQLException {
        String query = "SELECT name, price FROM products WHERE name LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, "%" + searchTerm + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("name") + " - " + rs.getString("price"));
        }
    }
}
