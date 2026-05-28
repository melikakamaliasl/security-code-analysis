package xss;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class VulnerableComment {

    private String formatComment(String user, String comment) {
        return "<p><strong>" + user + ":</strong> " + comment + "</p>";
    }

    public void postComment(Connection conn, String username, String comment) throws SQLException {
        String q = "INSERT INTO comments (username, comment) VALUES ('"
                + username + "', '" + comment + "')";
        conn.createStatement().executeUpdate(q);
    }

    public void showComments(Connection conn, HttpServletResponse response)
            throws SQLException, IOException {
        ResultSet rs = conn.createStatement()
                .executeQuery("SELECT username, comment FROM comments");
        PrintWriter out = response.getWriter();
        while (rs.next()) {
            out.println(formatComment(rs.getString("username"), rs.getString("comment")));
        }
    }
}