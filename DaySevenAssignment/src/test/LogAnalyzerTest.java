package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.LogAnalyzer;

class LogAnalyzerTest {

    private static final String RES_DIR    = "src/test/resources";
    private static final String OUT_DIR    = "resources";
    private static final String SUMMARY    = "resources/summary.txt";

    private final PrintStream origOut = System.out;
    private final Path summaryPath    = Path.of(SUMMARY);
    private final Path outDirPath     = Path.of(OUT_DIR);

    /**
     * Returns the path to a file inside a named test case folder.
     *
     * @param testCase folder name such as exec001
     * @param filename name of the file inside the folder
     * @return constructed Path object
     */
    Path resPath(String testCase, String filename) {
        return Path.of(String.format("%s/%s/%s", RES_DIR, testCase, filename));
    }

    /**
     * Invokes the analyzer with the given log file and returns
     * the text printed to standard output during execution.
     *
     * @param logFile path to the log file to analyze
     * @return captured console output as a string
     */
    String runAnalyzer(String logFile) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        LogAnalyzer.main(new String[]{ logFile });
        System.setOut(origOut);
        return out.toString();
    }

    /**
     * Sets up the output directory and removes any leftover
     * summary file before each test.
     */
    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(outDirPath);
        deleteSummary();
    }

    /**
     * Restores standard output and removes the generated summary
     * file after each test.
     */
    @AfterEach
    void cleanUp() throws IOException {
        System.setOut(origOut);
        deleteSummary();
    }

    /**
     * Removes the summary file or directory at the output path
     * if it exists.
     */
    void deleteSummary() throws IOException {
        if (Files.isDirectory(summaryPath)) {
            Files.delete(summaryPath);
        } else {
            Files.deleteIfExists(summaryPath);
        }
    }

    /**
     * Tests that valid log entries with multiple levels produce
     * a correctly formatted summary file and success message.
     */
    @Test
    void exec001() throws IOException {
        String actual   = runAnalyzer(resPath("exec001", "server.log").toString());
        String expected = Files.readString(resPath("exec001", "summary.txt"));
        String result   = Files.readString(summaryPath);

        assertEquals(expected, result);
        assertTrue(actual.contains(
                "Analysis complete. Summary written to summary.txt"));
    }

    /**
     * Tests that a line without timestamp brackets is skipped and
     * a warning is printed while valid entries are still processed.
     */
    @Test
    void exec002() throws IOException {
        String actual   = runAnalyzer(resPath("exec002", "server.log").toString());
        String expected = Files.readString(resPath("exec002", "summary.txt"));
        String result   = Files.readString(summaryPath);

        assertEquals(expected, result);
        assertTrue(actual.contains(
                "Skipping malformed line: CORRUPTED ENTRY NO BRACKETS"));
        assertTrue(actual.contains(
                "Analysis complete. Summary written to summary.txt"));
    }

    /**
     * Tests that a log entry with an unsupported level is skipped
     * and does not appear in the output summary counts.
     */
    @Test
    void exec003() throws IOException {
        String actual   = runAnalyzer(resPath("exec003", "server.log").toString());
        String expected = Files.readString(resPath("exec003", "summary.txt"));
        String result   = Files.readString(summaryPath);

        assertEquals(expected, result);
        assertTrue(actual.contains(
                "Skipping malformed line: "
                + "[2025-01-15 10:01:45] CRITICAL: System overload detected"));
    }

    /**
     * Tests that a log entry with a missing message body is skipped
     * and the remaining valid entries are counted correctly.
     */
    @Test
    void exec004() throws IOException {
        String actual   = runAnalyzer(resPath("exec004", "server.log").toString());
        String expected = Files.readString(resPath("exec004", "summary.txt"));
        String result   = Files.readString(summaryPath);

        assertEquals(expected, result);
        assertTrue(actual.contains(
                "Skipping malformed line: [2025-01-15 11:01:11] WARN"));
    }

    /**
     * Tests that a missing input file causes an error message and
     * prevents any summary file from being created.
     */
    @Test
    void exec005() {
        String actual = runAnalyzer(
                resPath("exec005", "ghost.log").toString());

        assertTrue(actual.contains("Log file not found."));
        assertFalse(Files.exists(summaryPath));
    }

    /**
     * Tests that timestamps are correctly identified as earliest and
     * latest even when log entries are not in chronological order.
     */
    @Test
    void exec006() throws IOException {
        String actual   = runAnalyzer(resPath("exec006", "server.log").toString());
        String expected = Files.readString(resPath("exec006", "summary.txt"));
        String result   = Files.readString(summaryPath);

        assertEquals(expected, result);
        assertTrue(actual.contains(
                "Analysis complete. Summary written to summary.txt"));
    }

    /**
     * Tests that an empty log file produces a valid summary with
     * zero entry counts and null timestamp values.
     */
    @Test
    void exec007() throws IOException {
        String actual   = runAnalyzer(resPath("exec007", "server.log").toString());
        String expected = Files.readString(resPath("exec007", "summary.txt"));
        String result   = Files.readString(summaryPath);

        assertEquals(expected, result);
        assertTrue(actual.contains(
                "Analysis complete. Summary written to summary.txt"));
    }

    /**
     * Tests that the program prints a write error when the output
     * path is occupied by a directory rather than a writable file.
     */
    @Test
    void exec008() throws IOException {
        Files.createDirectory(summaryPath);

        String actual = runAnalyzer(
                resPath("exec001", "server.log").toString());

        assertTrue(actual.contains("Error writing summary file."));
    }

    /**
     * Tests that the program prints a read error when the input
     * path is a directory rather than a readable file.
     */
    @Test
    void exec009() throws IOException {
        Path fakeLog = resPath("exec009", "server.log");

        Files.deleteIfExists(fakeLog);
        Files.createDirectories(fakeLog);

        try {
            String actual = runAnalyzer(fakeLog.toString());

            assertTrue(
                    actual.contains("Error reading file.") ||
                    actual.contains("Log file not found."));
            assertFalse(Files.exists(summaryPath));
        } finally {
            Files.deleteIfExists(fakeLog);
        }
    }
}