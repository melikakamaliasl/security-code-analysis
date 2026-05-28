package xss;

import javax.servlet.http.*;
import java.io.*;
import java.util.Base64;

public class VulnerableSearch {

    private static final String TEMPLATE_START = "PGgyPlJlc3VsdHMgZm9yOiA=";
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