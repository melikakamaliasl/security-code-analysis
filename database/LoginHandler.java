package sql_injection;

import java.sql.*;
import java.io.*;
import java.util.Properties;

public class VulnerableLogin {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = new FileInputStream("queries.properties")) {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(Connection conn, String username, String password) throws SQLException {
        String query = String.format(props.getProperty("login.query"), username, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs.next();
    }
}