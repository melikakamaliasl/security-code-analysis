package xss;

import javax.servlet.http.*;
import java.io.*;

public class VulnerableSearch {

    private String renderResults(String userInput) {
        return "<h2>Results for: " + userInput + "</h2>";
    }

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String query = request.getParameter("q");
        PrintWriter out = response.getWriter();
        out.println(renderResults(query));
    }
}