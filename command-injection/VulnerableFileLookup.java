package command_injection;

import java.io.*;

public class VulnerableFileLookup {

    private String[] buildReadCommand(String path) {
        return new String[]{"sh", "-c", "cat " + path};
    }

    public String readFile(String filename) throws IOException {
        Process process = Runtime.getRuntime().exec(buildReadCommand(filename));
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