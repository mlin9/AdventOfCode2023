package AdventOfCode2023;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {

    private static final String REGEX_NUMS = "(one)|(two)|(three)|(four)|(five)|(six)|(seven)|(eight)|(nine)|(zero)|\\d";

    public static void main(String[] args) throws URISyntaxException, IOException {
        printSums();
    }

    public static void printSums() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get((new File("Day1.txt")).getAbsolutePath()),
                StandardCharsets.UTF_8);
        System.out.println(sumPartOne(lines.toArray(new String[lines.size()])));
        System.out.println(sumPartTwo(lines.toArray(new String[lines.size()])));
    }

    public static int sumPartOne(String[] document) {
        int sum = 0;
        for (String line : document) {
            ArrayList<Integer> digits = new ArrayList<>();
            for (Character character : line.toCharArray()) {
                int digit = parseChar(character);
                if (digit > 0)
                    digits.add(digit);
            }
            sum += digits.getFirst() * 10 + digits.getLast();
        }
        return sum;
    }

    public static int sumPartTwo(String[] document) {
        int sum = 0;
        for (String line : document) {
            ArrayList<String> matches = regexFirstLast(line, REGEX_NUMS);
            int first = parseString(matches.getFirst()) * 10;
            int last = parseString(matches.getLast());
            sum += first + last;
        }
        return sum;
    }

    public static ArrayList<String> regexFirstLast(String input, String pattern) {
        ArrayList<String> matches = new ArrayList<>();
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        while (matcher.find())
            matches.add(matcher.group());
        return new ArrayList<String>(Arrays.asList(matches.getFirst(), matches.getLast()));
    }

    public static int parseString(String input) {
        switch (input) {
            case "one":
                return 1;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
            case "zero":
                return 0;
            default:
                return parseChar(input.charAt(0));
        }
    }

    public static int parseChar(char input) {
        switch ((int) input) {
            case 48:
                return 0;
            case 49:
                return 1;
            case 50:
                return 2;
            case 51:
                return 3;
            case 52:
                return 4;
            case 53:
                return 5;
            case 54:
                return 6;
            case 55:
                return 7;
            case 56:
                return 8;
            case 57:
                return 9;
            default:
                return -1;
        }
    }
}