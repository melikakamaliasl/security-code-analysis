package command_injection;

import java.io.*;

// Safer ping - validates input and uses ProcessBuilder instead of sh -c
public class SafePing {

    public String ping(String host) throws IOException {
        if (!host.matches("^[a-zA-Z0-9.\\-]+$")) {
            throw new IllegalArgumentException("Invalid host");
        }

        ProcessBuilder pb = new ProcessBuilder("ping", "-c", "4", host);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        return output.toString();
    }
}
