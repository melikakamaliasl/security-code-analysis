package xss;

import javax.servlet.http.*;
import java.io.*;

public class VulnerableErrorPage {

    private String buildErrorPage(String msg) {
        String[] parts = {"<html><body><div class='error'>", msg, "</div></body></html>"};
        return String.join("", parts);
    }

    public void showError(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String message = request.getParameter("message");
        response.getWriter().println(buildErrorPage(message));
    }
}