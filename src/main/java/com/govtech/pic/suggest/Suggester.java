package com.govtech.pic.suggest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Suggester implements LogEntryProcessor {
    public Suggester() {

    }

    ArrayList<String> result = new ArrayList<>();

    /*
      @param logEntryMap - Map containing keys and values in each log line
      This is a call back method that LogReader.read() calls on every line in the input file, after parsing them into key-value pairs
    */
    public void processEntries(Map<String, String> logEntryMap) {
        for (Map.Entry<String, String> entry : logEntryMap.entrySet()) {
         String tValue = entry.get("t");

        // Get the match value based on the t value
        String matchValue = getMatchValue(entry, tValue);
        result.add(matchValue);
        System.out.println("Value for 't': " + tValue);
        System.out.println("Corresponding 'match' value: " + matchValue);
        }
    }

    // Get the match value based on the t value
    public static String getMatchValue(Map<String, String> params, String tValue) {
        // Directly access the match parameter and check against tValue
        String matchValue = params.get("match");
        if (matchValue != null && matchValue.equalsIgnoreCase(tValue)) {
            return matchValue;
        }
        return null; // Return null if no match found
    }

    /**
     * Returns a list of suggestions for a given user query.
     * @param query the string that the user has typed so far
     * @param k the maximum number of suggestions requested
     */
    public List<String> getTopSuggestions(String query, int k) {

       ArrayList<String> newSearchResult = new ArrayList<>();
         // Define the range to get values (0 to 6)
        int startIndex = 0;
        int endIndex = Math.min(k, list.size() - 1); // Ensure we don't go out of bounds

        // Retrieve values from the list
        for (int i = startIndex; i <= endIndex; i++) {
            String value = list.get(i);
            newSearchResult.add(value);
            System.out.println("Index: " + i + ", Value: " + value);
        }

        return newSearchResult;
    }

    // main() for command-line testing
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Need to provide log file (query.log) as argument");
        }
        final String inputFile = args[0];
        Suggester suggester = new Suggester();
        LogReader logReader = new LogReader(inputFile, suggester);

        long elapsedTime = -System.currentTimeMillis();
        logReader.read();
        elapsedTime += System.currentTimeMillis();

        System.out.println(elapsedTime + "ms to read file");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        double total = (double) Runtime.getRuntime().totalMemory() / (double) (1024 * 1024);
        double free = (double) Runtime.getRuntime().freeMemory() / (double) (1024 * 1024);
        System.out.printf("Total: %.2fMB, Free: %.2fMB, Used: %.2fMB%n", total, free, total - free);

        try {
            System.out.println("Type 'quit' or 'exit' when you're done.");
            while (true) {
                System.out.print("query> ");
                String line = in.readLine();
                if ("".equals(line)) continue;
                if (line == null || "quit".equals(line) || "exit".equals(line)) break;

                elapsedTime = -System.currentTimeMillis();
                List<String> suggestions = suggester.getTopSuggestions(line, 6);
                elapsedTime += System.currentTimeMillis();

                System.out.println("Suggestions for '" + line + "' " + suggestions + " fetched in " + elapsedTime + "ms");
            }
            System.out.println();
        } finally {
            in.close();
        }
    }
}
