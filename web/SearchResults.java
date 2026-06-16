package web;

import javax.servlet.http.*;
import java.io.*;
import java.util.Properties;

public class SearchResults {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = new FileInputStream("queries.properties")) {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String query = request.getParameter("q");
        String open = props.getProperty("xss.search.open");
        String close = props.getProperty("xss.search.close");
        response.getWriter().println(open + query + close);
    }
}