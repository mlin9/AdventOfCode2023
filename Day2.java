package AdventOfCode2023;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {
    private final static String REGEX_CUBES = "\\d+((red)|(green)|(blue))";
    private final static String REGEX_COLOR = "(red)|(green)|(blue)";
    private final static String REGEX_INT = "\\d+";
    private final static int RED = 0;
    private final static int GREEN = 1;
    private final static int BLUE = 2;

    public static void main(String[] args) throws IOException {
        printPartOne();
    }

    static void printPartOne() throws IOException {
        File file = new File("Day2.txt");
        Path path = Paths.get(file.getAbsolutePath());
        List<String> list = Files.readAllLines(path, StandardCharsets.UTF_8);
        String[] strings = (String[]) list.toArray(new String[list.size()]);
        int[] bag = { 12, 13, 14 };
        System.out.println(sumPartOne(strings, bag));
    }

    static int sumPartOne(String[] document, int[] bag) {
        int sum = 0;
        for (int row = 0; row < document.length; row++) {
            boolean hasCapacity = true;
            String[] sets = document[row].trim().toLowerCase().split(";");
            for (String set : sets) {
                String[] subsets = set.replaceAll("\\s", "").split(",");
                for (String subset : subsets) {
                    Matcher cubeMatcher = Pattern.compile(REGEX_CUBES).matcher(subset);
                    ArrayList<String> cubeMatches = new ArrayList<>();
                    while (cubeMatcher.find()) {
                        cubeMatches.add(cubeMatcher.group());
                    }

                    for (String cubeString : cubeMatches) {
                        if (hasCapacity) {
                            Matcher intMatcher = Pattern.compile(REGEX_INT).matcher(cubeString);
                            Matcher colorMatcher = Pattern.compile(REGEX_COLOR).matcher(cubeString);
                            intMatcher.find();
                            colorMatcher.find();
                            int count = Integer.parseInt(intMatcher.group());
                            String color = colorMatcher.group();
                            switch (color) {
                                case "red":
                                    hasCapacity = bag[RED] >= count;
                                    break;
                                case "green":
                                    hasCapacity = bag[GREEN] >= count;
                                    break;
                                case "blue":
                                    hasCapacity = bag[BLUE] >= count;
                                    break;
                            }
                        }
                    }
                }
            }

            if (hasCapacity) {
                sum += row + 1;
            }
        }

        return sum;
    }
}