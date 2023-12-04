package AdventOfCode2023;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Trebuchet {
    public static void main(String[] args) throws URISyntaxException, IOException {
        sumPartOne();
        sumPartTwo();
    }

    public static void sumPartOne() throws IOException {
        Path path1 = Paths.get("C:/Users/Michelle Lin/Projects/Java/AdventOfCode/input_day1");
        List<String> lines1 = Files.readAllLines(path1, StandardCharsets.UTF_8);
        String[] document1 = (String[]) lines1.toArray(new String[lines1.size()]);
        System.out.println(sumDigits(document1));
    }

    public static void sumPartTwo() throws IOException {
        Path path2 = Paths.get("C:/Users/Michelle Lin/Projects/Java/AdventOfCode/input_day1");
        List<String> lines2 = Files.readAllLines(path2, StandardCharsets.UTF_8);
        String[] document2 = (String[]) lines2.toArray(new String[lines2.size()]);
        System.out.println(sumMatches(document2));
    }

    public static int sumMatches(String[] document) {
        int sum = 0;
        String regex = "(one)|(two)|(three)|(four)|(five)|(six)|(seven)|(eight)|(nine)|(zero)|\\d";
        for (int i = 0; i < document.length; i++) {
            ArrayList<Integer> digits = new ArrayList<>();
            Matcher matcher = Pattern.compile(regex).matcher(document[i]);
            while(matcher.find()) {
                digits.add(parseString(matcher.group()));
            }
            sum = sum + digits.getFirst() * 10 + digits.getLast();
        }
        return sum;
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
                return parseInt(input.charAt(0));
        }
    }

    public static int sumDigits(String[] document) {
        int sum = 0;
        for (int i = 0; i < document.length; i++) {
            ArrayList<Integer> digits = new ArrayList<>();
            for (int j = 0; j < document[i].length(); j++) {
                int digit = parseInt(document[i].charAt(j));

                if (digit > 0) {
                    digits.add(digit);
                }
            }
            sum = sum + digits.getFirst() * 10 + digits.getLast();
        }
        return sum;
    }

    public static int parseInt(char input) {
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