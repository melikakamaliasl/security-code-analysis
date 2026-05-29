package xss;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

// Comment section - stores and displays user comments
public class VulnerableComment {

    public void postComment(Connection conn, String username, String comment) throws SQLException {
        String query = "INSERT INTO comments (username, comment) VALUES ('" + username + "', '" + comment + "')";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
    }

    public void showComments(Connection conn, HttpServletResponse response) throws SQLException, IOException {
        String query = "SELECT username, comment FROM comments";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        PrintWriter out = response.getWriter();
        while (rs.next()) {
            out.println("<p><strong>" + rs.getString("username") + ":</strong> " + rs.getString("comment") + "</p>");
        }
    }
}
