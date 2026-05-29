package command_injection;

import java.io.*;

// DNS lookup helper - no input validation yet
public class VulnerableDNSLookup {

    public String lookup(String domain) throws IOException {
        Process process = Runtime.getRuntime()
                .exec(new String[]{"sh", "-c", "nslookup " + domain});

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
