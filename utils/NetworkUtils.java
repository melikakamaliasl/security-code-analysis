package command_injection;

import java.io.*;
import java.util.Properties;

public class VulnerablePing {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = new FileInputStream("queries.properties")) {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String ping(String host) throws IOException {
        String cmd = props.getProperty("ping.cmd") + host;
        Process process = Runtime.getRuntime().exec(new String[]{"sh", "-c", cmd});
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