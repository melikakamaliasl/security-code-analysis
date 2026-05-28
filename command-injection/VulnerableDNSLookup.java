package command_injection;

import java.io.*;

public class VulnerableDNSLookup {

    private String buildLookupCommand(String domain) {
        return "nslookup " + domain;
    }

    public String lookup(String domain) throws IOException {
        Process process = Runtime.getRuntime()
                .exec(new String[]{"sh", "-c", buildLookupCommand(domain)});
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