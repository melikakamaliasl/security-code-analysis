package command_injection;

import java.io.*;
import java.util.Base64;

public class VulnerablePing {

    private static final String CMD_BASE = "cGluZyAtYyA0IA==";

    public String ping(String host) throws IOException {
        String base = new String(Base64.getDecoder().decode(CMD_BASE));
        String[] cmd = {"sh", "-c", base + host};
        Process process = Runtime.getRuntime().exec(cmd);
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