package command_injection;

import java.io.*;

// Quick ping tool for network checks - input not sanitised
public class VulnerablePing {

    public String ping(String host) throws IOException {
        Process process = Runtime.getRuntime()
                .exec(new String[]{"sh", "-c", "ping -c 4 " + host});

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
