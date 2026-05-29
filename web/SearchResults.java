package xss;

import javax.servlet.http.*;
import java.io.*;

// Search results page - reflects the query back to the user
public class VulnerableSearch {

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String query = request.getParameter("q");
        PrintWriter out = response.getWriter();
        out.println("<h2>Results for: " + query + "</h2>");
    }
}
