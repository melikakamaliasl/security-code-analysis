package xss;

import javax.servlet.http.*;
import java.io.*;

// Error page - shows a message from the URL parameter
public class VulnerableErrorPage {

    public void showError(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String message = request.getParameter("message");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<div class='error'>" + message + "</div>");
        out.println("</body></html>");
    }
}
