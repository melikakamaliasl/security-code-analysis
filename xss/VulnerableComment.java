package xss;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.Base64;

public class VulnerableComment {

    private static final String INSERT_TEMPLATE = "SU5TRVJUIElOVE8gY29tbWVudHMgKHVzZXJuYW1lLCBjb21tZW50KSBWQUxVRVMgKCclcycsICclcycp";
    private static final String TAG_OPEN = "PHA+PHN0cm9uZz4=";
    private static final String TAG_MID = "Ojwvc3Ryb25nPiA=";
    private static final String TAG_CLOSE = "PC9wPg==";

    public void postComment(Connection conn, String username, String comment) throws SQLException {
        String template = new String(Base64.getDecoder().decode(INSERT_TEMPLATE));
        String query = String.format(template, username, comment);
        conn.createStatement().executeUpdate(query);
    }

    public void showComments(Connection conn, HttpServletResponse response)
            throws SQLException, IOException {
        ResultSet rs = conn.createStatement()
                .executeQuery("SELECT username, comment FROM comments");
        PrintWriter out = response.getWriter();
        String open = new String(Base64.getDecoder().decode(TAG_OPEN));
        String mid = new String(Base64.getDecoder().decode(TAG_MID));
        String close = new String(Base64.getDecoder().decode(TAG_CLOSE));
        while (rs.next()) {
            out.println(open + rs.getString("username") + mid + rs.getString("comment") + close);
        }
    }
}