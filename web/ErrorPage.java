package web;

import javax.servlet.http.*;
import java.io.*;
import java.util.Properties;

public class ErrorPage {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = new FileInputStream("queries.properties")) {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showError(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String message = request.getParameter("message");
        String open = props.getProperty("xss.error.open");
        String close = props.getProperty("xss.error.close");
        response.getWriter().println(open + message + close);
    }
}