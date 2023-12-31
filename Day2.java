package AdventOfCode2023;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
    printSums();
  }

  static void printSums() throws IOException {
    List<String> list = Files.readAllLines(Paths.get((new File("Day2.txt")).getAbsolutePath()),
        StandardCharsets.UTF_8);
    String[] strings = (String[]) list.toArray(new String[list.size()]);
    Map<String, Integer> bag = Map.of("red", 12, "green", 13, "blue", 14);
    System.out.println(sumPartOne(strings, bag));
    System.out.println(sumPartTwo(strings));
  }

  static int sumPartOne(String[] document, Map<String, Integer> bag) {
    int sum = 0;
    for (int i = 0; i < document.length; i++) {
      boolean hasCapacity = true;
      for (String set : document[i].trim().toLowerCase().split(";"))
        for (String subset : set.replaceAll("\\s", "").split(",")) {
          String[] cubes = regexAll(subset, REGEX_CUBES);
          for (int j = 0; j < cubes.length && hasCapacity; j++)
            hasCapacity = bag.get(regex(cubes[j], REGEX_COLOR)) >= Integer
                .parseInt(regex(cubes[j], REGEX_INT));
        }
      sum += hasCapacity ? i + 1 : 0;
    }
    return sum;
  }

  static int sumPartTwo(String[] document) {
    int sum = 0;
    for (String line : document) {
      Map<String, Integer> maximums = new HashMap<String, Integer>(Map.of("red", 0, "green", 0, "blue", 0));
      int power = 1;
      for (String set : line.trim().toLowerCase().split(";"))
        for (String subset : set.replaceAll("\\s", "").split(","))
          for (String cube : regexAll(subset, REGEX_CUBES))
            if (maximums.get(regex(cube, REGEX_COLOR)) < Integer.parseInt(regex(cube, REGEX_INT)))
              maximums.put(regex(cube, REGEX_COLOR), Integer.parseInt(regex(cube, REGEX_INT)));
      for (int value : maximums.values())
        power *= value;
      sum += power;
    }
    return sum;
  }

  public static String[] regexAll(String input, String pattern) {
    ArrayList<String> matches = new ArrayList<>();
    Matcher matcher = Pattern.compile(pattern).matcher(input);
    while (matcher.find())
      matches.add(matcher.group());
    return matches.toArray(new String[matches.size()]);
  }

  public static String regex(String input, String pattern) {
    Matcher matcher = Pattern.compile(pattern).matcher(input);
    if (matcher.find())
      return matcher.group();
    return null;
  }
}