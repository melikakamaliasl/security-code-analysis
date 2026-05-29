package command_injection;

import java.io.*;

// Reads a file using cat - should probably just use Java file reading instead
public class VulnerableFileLookup {

    public String readFile(String filename) throws IOException {
        Process process = Runtime.getRuntime()
                .exec(new String[]{"sh", "-c", "cat " + filename});

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
