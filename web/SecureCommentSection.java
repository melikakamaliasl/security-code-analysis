package web;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

// Fixed comment section - encodes before displaying
public class SecureCommentSection {

    public void postComment(Connection conn, String username, String comment) throws SQLException {
        String query = "INSERT INTO comments (username, comment) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, comment);
        stmt.executeUpdate();
    }

    public void showComments(Connection conn, HttpServletResponse response) throws SQLException, IOException {
        String query = "SELECT username, comment FROM comments";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        PrintWriter out = response.getWriter();
        while (rs.next()) {
            String username = encode(rs.getString("username"));
            String comment  = encode(rs.getString("comment"));
            out.println("<p><strong>" + username + ":</strong> " + comment + "</p>");
        }
    }

    private String encode(String input) {
        return input
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }
}
