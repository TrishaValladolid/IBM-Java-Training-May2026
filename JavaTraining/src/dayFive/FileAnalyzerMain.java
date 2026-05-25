import java.io.*;
import java.util.*;

class MalformedLogEntryException extends Exception {
    public MalformedLogEntryException(String message) {
        super(message);
    }
}

public class FileAnalyzerMain {
    public static void main(String[] args) {
        String filePath = "server.log";
        String outputPath = "summary.txt";
        String line;

        Map<String, Integer> levelCount = new HashMap<>();
        levelCount.put("INFO", 0);
        levelCount.put("WARN", 0);
        levelCount.put("ERROR", 0);

        List<String> errorMessages = new ArrayList<>();

        String earliest = null;
        String latest = null;
        int totalEntries = 0;

        // READ
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {
                try {
                    if (!line.startsWith("[") || !line.contains("]")) {
                        throw new MalformedLogEntryException("Invalid format: " + line);
                    }
                    if (!line.contains("INFO") && !line.contains("WARN") && !line.contains("ERROR")) {
                        throw new MalformedLogEntryException("Invalid level: " + line);
                    }
                    if (!line.contains(":")) {
                        throw new MalformedLogEntryException("Missing colon: " + line);
                    }

                    totalEntries++;

                    // A. count levels
                    if (line.contains("INFO")) {
                        levelCount.put("INFO", levelCount.get("INFO") + 1);
                    } else if (line.contains("WARN")) {
                        levelCount.put("WARN", levelCount.get("WARN") + 1);
                    } else if (line.contains("ERROR")) {
                        levelCount.put("ERROR", levelCount.get("ERROR") + 1);
                    }

                    // B. extract ERROR messages
                    if (line.contains("ERROR")) {
                        String message = line.split("ERROR: ")[1];
                        errorMessages.add(message);
                    }

                    // C. get timestamp
                    String timestamp = line.substring(1, line.indexOf("]"));
                    if (earliest == null || timestamp.compareTo(earliest) < 0) {
                        earliest = timestamp;
                    }
                    if (latest == null || timestamp.compareTo(latest) > 0) {
                        latest = timestamp;
                    }

                } catch (MalformedLogEntryException e) {
                    System.out.println("Malformed: " + e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // WRITE summary.txt
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {

            bw.write("Log Summary Report");
            bw.newLine();
            bw.write("------------------");
            bw.newLine();
            bw.write("Total Entries: " + totalEntries);
            bw.newLine();
            bw.write("INFO: " + levelCount.get("INFO"));
            bw.newLine();
            bw.write("WARN: " + levelCount.get("WARN"));
            bw.newLine();
            bw.write("ERROR: " + levelCount.get("ERROR"));
            bw.newLine();
            bw.newLine();
            bw.write("Error Messages:");
            bw.newLine();
            for (String msg : errorMessages) {
                bw.write("- " + msg);
                bw.newLine();
            }
            bw.newLine();
            bw.write("Earliest Timestamp: " + earliest);
            bw.newLine();
            bw.write("Latest Timestamp: " + latest);

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }

        System.out.println("Done. Check summary.txt");
    }
}