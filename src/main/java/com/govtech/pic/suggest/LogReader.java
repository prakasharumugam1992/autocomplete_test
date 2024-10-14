package com.govtech.pic.suggest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class LogReader {
    private final BufferedReader reader;
    private final LogEntryProcessor logEntryProcessor;

    public LogReader(String filename, LogEntryProcessor processor) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(filename))));
        this.logEntryProcessor = processor;
    }

    public void read() {
        try {
            String line;
            long lineCount = 0;
            while ((line = reader.readLine()) != null) {
                Map<String,String> map = Utils.getNameValues(line);
                logEntryProcessor.processEntries(map);
                lineCount++;
            }

            System.out.println("lineCount = " + lineCount);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}