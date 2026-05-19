package xss;

import javax.servlet.http.*;
import java.io.*;

// Fixed search - encodes output before rendering
public class SafeSearch {

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String query = request.getParameter("q");
        String safe = query
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");

        PrintWriter out = response.getWriter();
        out.println("<h2>Results for: " + safe + "</h2>");
    }
}
