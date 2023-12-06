package AdventOfCode2023;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {
    private final static String REGEX_CUBES = "\\d+((red)|(green)|(blue))";
    private final static String REGEX_COLOR = "(red)|(green)|(blue)";
    private final static String REGEX_INT = "\\d+";

    public static void main(String[] args) throws IOException {
        printPartOne();
    }

    static void printPartOne() throws IOException {
        File file = new File("Day2.txt");
        Path path = Paths.get(file.getAbsolutePath());
        List<String> list = Files.readAllLines(path, StandardCharsets.UTF_8);
        String[] strings = (String[]) list.toArray(new String[list.size()]);
        Map<String, Integer> bag = new HashMap<>();
        bag.put("red", 12);
        bag.put("green", 13);
        bag.put("blue", 14);
        System.out.println(sumPartOne(strings, bag));
    }

    static int sumPartOne(String[] document, Map<String, Integer> bag) {
        int sum = 0;
        for (int i = 0; i < document.length; i++) {
            boolean hasCapacity = true;
            for (String set : document[i].trim().toLowerCase().split(";")) {
                for (String subset : set.replaceAll("\\s", "").split(",")) {
                    String[] cubeCounts = regexAll(subset, REGEX_CUBES);
                    for (int j = 0; j < cubeCounts.length && hasCapacity; j++) {
                        hasCapacity = bag.get(regex(cubeCounts[j], REGEX_COLOR)) >= Integer
                                .parseInt(regex(cubeCounts[j], REGEX_INT));
                    }
                }
            }
            sum = sum + (hasCapacity ? i + 1 : 0);
        }
        return sum;
    }

    public static String[] regexAll(String input, String pattern) {
        ArrayList<String> matches = new ArrayList<>();
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches.toArray(new String[matches.size()]);
    }

    public static String regex(String input, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}