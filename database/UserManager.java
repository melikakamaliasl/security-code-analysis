package database;

import java.sql.*;
import java.io.*;
import java.util.Properties;

public class UserManager {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = new FileInputStream("queries.properties")) {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(Connection conn, String userId) throws SQLException {
        String query = props.getProperty("delete.query") + userId;
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        System.out.println("User deleted.");
    }
}