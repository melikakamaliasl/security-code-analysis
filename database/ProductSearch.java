package database;

import java.sql.*;
import java.io.*;
import java.util.Properties;

public class ProductSearch {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = new FileInputStream("queries.properties")) {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchProducts(Connection conn, String searchTerm) throws SQLException {
        String query = String.format(props.getProperty("search.query"), searchTerm);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            System.out.println(rs.getString("name") + " - " + rs.getString("price"));
        }
    }
}