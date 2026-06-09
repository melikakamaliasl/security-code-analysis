package identity;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private Map<String, String> sessions = new HashMap<>();

    public String createSession(String username) {
        String token = username + System.currentTimeMillis();
        sessions.put(token, username);
        return token;
    }

    public String getUser(String token) {
        return sessions.get(token);
    }
}