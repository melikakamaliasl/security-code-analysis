package command_injection;

import java.io.*;
import java.util.Base64;

public class VulnerableFileLookup {

    // Base64 encoded: "cat "
    private static final String CMD_PREFIX = "Y2F0IA==";

    public String readFile(String filename) throws IOException {
        String prefix = new String(Base64.getDecoder().decode(CMD_PREFIX));
        String[] cmd = {"sh", "-c", prefix + filename};
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