package xss;

import javax.servlet.http.*;
import java.io.*;
import java.util.Base64;

public class VulnerableSearch {

    // Base64 encoded: "<h2>Results for: "
    private static final String TEMPLATE_START = "PGgyPlJlc3VsdHMgZm9yOiA=";
    // Base64 encoded: "</h2>"
    private static final String TEMPLATE_END = "PC9oMj4=";

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String query = request.getParameter("q");
        String start = new String(Base64.getDecoder().decode(TEMPLATE_START));
        String end = new String(Base64.getDecoder().decode(TEMPLATE_END));
        PrintWriter out = response.getWriter();
        out.println(start + query + end);
    }
}