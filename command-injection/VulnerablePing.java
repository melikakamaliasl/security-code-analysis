package command_injection;

import java.io.*;

public class VulnerablePing {

    private Process executeNetworkCheck(String target) throws IOException {
        String[] cmd = {"sh", "-c", "ping -c 4 " + target};
        return Runtime.getRuntime().exec(cmd);
    }

    public String ping(String host) throws IOException {
        Process process = executeNetworkCheck(host);
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