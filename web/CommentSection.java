package web;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class CommentSection{

    private static final Properties props = new Properties();

    static {
        try (InputStream is = new FileInputStream("queries.properties")) {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void postComment(Connection conn, String username, String comment) throws SQLException {
        String query = String.format(props.getProperty("xss.comment.insert"), username, comment);
        conn.createStatement().executeUpdate(query);
    }

    public void showComments(Connection conn, HttpServletResponse response)
            throws SQLException, IOException {
        ResultSet rs = conn.createStatement()
                .executeQuery("SELECT username, comment FROM comments");
        PrintWriter out = response.getWriter();
        String open = props.getProperty("xss.comment.open");
        String mid = props.getProperty("xss.comment.mid");
        String close = props.getProperty("xss.comment.close");
        while (rs.next()) {
            out.println(open + rs.getString("username") + mid + rs.getString("comment") + close);
        }
    }
}