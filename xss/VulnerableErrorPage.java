package xss;

import javax.servlet.http.*;
import java.io.*;
import java.util.Base64;

public class VulnerableErrorPage {

    private static final String PAGE_OPEN = "PGh0bWw+PGJvZHk+PGRpdiBjbGFzcz0nZXJyb3InPg==";
    private static final String PAGE_CLOSE = "PC9kaXY+PC9ib2R5PjwvaHRtbD4=";

    public void showError(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String message = request.getParameter("message");
        String open = new String(Base64.getDecoder().decode(PAGE_OPEN));
        String close = new String(Base64.getDecoder().decode(PAGE_CLOSE));
        response.getWriter().println(open + message + close);
    }
}