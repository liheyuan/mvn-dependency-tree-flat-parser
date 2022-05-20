package com.coder4.mvndeptree.flatparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class FlatParser {

    private static Set<Character> DEP_START_SYMBOL_SET;

    static {
        DEP_START_SYMBOL_SET = new HashSet<>();
        DEP_START_SYMBOL_SET.add('+');
        DEP_START_SYMBOL_SET.add('|');
        DEP_START_SYMBOL_SET.add('\\');
        DEP_START_SYMBOL_SET.add('-');
    }

    public static FlatParseResult parse(File file) throws FileNotFoundException {
        try (Scanner scan = new Scanner(file)) {
            return parse(scan);
        }
    }

    public static FlatParseResult parse(String str) {
        try (Scanner scan = new Scanner(str)) {
            return parse(scan);
        }
    }

    public static FlatParseResult parse(Scanner scan) {

        FlatParseResult result = new FlatParseResult();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            // Step 1: Trim
            line = line.trim();

            // Step 2 : Check if is dependency line
            if (!line.isEmpty() && DEP_START_SYMBOL_SET.contains(line.charAt(0))) {
                // Step 3 : Get Dependency Part
                int pos = line.lastIndexOf(" ");
                if (pos != -1) {
                    // Step 4 : split parts
                    String depPart = line.substring(pos + 1);
                    String[] parts = depPart.split(":");
                    if (parts.length != 5) {
                        continue;
                    }

                    // Step 5 : Check if any part empty
                    boolean anyEmpty = Stream.of(parts).anyMatch(s -> s == null || s.isEmpty());
                    if (anyEmpty) {
                        continue;
                    }

                    // Step 6 : Add to result
                    FlatParseItem item = new FlatParseItem();
                    item.setGroup(parts[0]);
                    item.setArtifactId(parts[1]);
                    item.setType(parts[2]);
                    item.setVersion(parts[3]);
                    item.setScope(parts[4]);

                    result.addItem(item);
                }
            }
        }

        return result;
    }

}
