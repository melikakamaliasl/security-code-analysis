package utils;

import java.io.*;
import java.nio.file.*;

// Reads a file safely using Java instead of shelling out
public class SecureFileReader {

    private static final String ALLOWED_DIR = "/var/app/reports/";

    public String readFile(String filename) throws IOException {
        if (!filename.matches("^[a-zA-Z0-9.\\-_]+$")) {
            throw new IllegalArgumentException("Invalid filename");
        }

        Path filePath = Paths.get(ALLOWED_DIR, filename).normalize();

        if (!filePath.startsWith(ALLOWED_DIR)) {
            throw new SecurityException("Access denied");
        }

        return new String(Files.readAllBytes(filePath));
    }
}
