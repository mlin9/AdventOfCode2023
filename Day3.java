package AdventOfCode2023;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3 {
  private final static int BOTTOM_LEFT = 5;
  private final static int BOTTOM_MIDDLE = 6;
  private final static int BOTTOM_RIGHT = 7;
  private final static int END = 19600;
  private final static int LEFT = 3;
  private final static int RIGHT = 4;
  private final static int TOP_LEFT = 0;
  private final static int TOP_MIDDLE = 1;
  private final static int TOP_RIGHT = 2;
  private final static int WIDTH = 140;

  public static void main(String[] args) throws IOException {
    printSums();
  }

  static void printSums() throws IOException {
    char[] text = Files.readString(Paths.get((new File("Day3.txt")).getAbsolutePath()),
        StandardCharsets.UTF_8).replaceAll("\\s", "").replaceAll("\n", "").toCharArray();
    System.out.println(sumPartOne(text));
  }

  static int sumPartOne(char[] text) {
    ArrayList<Integer> locations = new ArrayList<>();
    ArrayList<Integer> values = new ArrayList<>();
    for (int i = 0; i < END; i++)
      if (symbol(text[i]))
        locations.add(i);
    for (int index : locations) {
      int[] peek = proximity(index);
      int temp = 0;
      int bottomLeft = digit(text[peek[BOTTOM_LEFT]]) ? 1 : 0;
      int bottomMiddle = digit(text[peek[BOTTOM_MIDDLE]]) ? 1 : 0;
      int bottomRight = digit(text[peek[BOTTOM_RIGHT]]) ? 1 : 0;
      int topLeft = digit(text[peek[TOP_LEFT]]) ? 1 : 0;
      int topMiddle = digit(text[peek[TOP_MIDDLE]]) ? 1 : 0;
      int topRight = digit(text[peek[TOP_RIGHT]]) ? 1 : 0;
      List<Character> bottom_list = Arrays.asList(((char) (bottomLeft + '0')), ((char) (bottomMiddle + '0')), ((char) (bottomRight + '0')));
      List<Character> top_list = Arrays.asList(((char) (topLeft + '0')), ((char) (topMiddle + '0')), ((char) (topRight + '0')));
      StringBuilder stringBuilderBottom = new StringBuilder();
      StringBuilder stringBuilderTop = new StringBuilder();
      for (Character c : bottom_list)
        stringBuilderBottom.append(c);
      for (Character c : top_list)
        stringBuilderTop.append(c);

      switch (stringBuilderTop.toString()) {
        case "111":
        case "110":
        case "100":
          StringBuilder sbLeft = new StringBuilder();
          for (int j = peek[TOP_LEFT]; j > 0 && digit(text[j]); j--)
            temp = j;
          for (int k = temp; k < END && digit(text[k]); k++)
            sbLeft.append(text[k]);
          values.add(Integer.parseInt(sbLeft.toString()));
          break;
        case "010":
        case "011":
          StringBuilder sbMiddle = new StringBuilder();
          for (int k = peek[TOP_MIDDLE]; k < END && digit(text[k]); k++)
            sbMiddle.append(text[k]);
          values.add(Integer.parseInt(sbMiddle.toString()));
          break;
        case "101":
          StringBuilder sbSplitLeft = new StringBuilder();
          for (int j = peek[TOP_LEFT]; j > 0 && digit(text[j]); j--)
            temp = j;
          for (int k = temp; k < END && digit(text[k]); k++)
            sbSplitLeft.append(text[k]);
          values.add(Integer.parseInt(sbSplitLeft.toString()));
          StringBuilder sbSplitRight = new StringBuilder();
          for (int k = peek[TOP_RIGHT]; k < END && digit(text[k]); k++)
            sbSplitRight.append(text[k]);
          values.add(Integer.parseInt(sbSplitRight.toString()));
          break;
        case "001":
          StringBuilder sbRight = new StringBuilder();
          for (int k = peek[TOP_RIGHT]; k < END && digit(text[k]); k++)
            sbRight.append(text[k]);
          values.add(Integer.parseInt(sbRight.toString()));
          break;
      }

      switch (stringBuilderBottom.toString()) {
        case "111":
        case "110":
        case "100":
          StringBuilder sbLeft = new StringBuilder();
          for (int j = peek[BOTTOM_LEFT]; j > 0 && digit(text[j]); j--)
            temp = j;
          for (int k = temp; k < END && digit(text[k]); k++)
            sbLeft.append(text[k]);
          values.add(Integer.parseInt(sbLeft.toString()));
          break;
        case "010":
        case "011":
          StringBuilder sbMiddle = new StringBuilder();
          for (int k = peek[BOTTOM_MIDDLE]; k < END && digit(text[k]); k++)
            sbMiddle.append(text[k]);
          values.add(Integer.parseInt(sbMiddle.toString()));
          break;
        case "101":
          StringBuilder sbSplitLeft = new StringBuilder();
          for (int j = peek[BOTTOM_LEFT]; j > 0 && digit(text[j]); j--)
            temp = j;
          for (int k = temp; k < END && digit(text[k]); k++)
            sbSplitLeft.append(text[k]);
          values.add(Integer.parseInt(sbSplitLeft.toString()));
          StringBuilder sbSplitRight = new StringBuilder();
          for (int k = peek[BOTTOM_RIGHT]; k < END && digit(text[k]); k++)
            sbSplitRight.append(text[k]);
          values.add(Integer.parseInt(sbSplitRight.toString()));
          break;
        case "001":
          StringBuilder sbRight = new StringBuilder();
          for (int k = peek[BOTTOM_RIGHT]; k < END && digit(text[k]); k++)
            sbRight.append(text[k]);
          values.add(Integer.parseInt(sbRight.toString()));
          break;
      }

      if (digit(text[peek[LEFT]])) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = peek[LEFT]; j > 0 && digit(text[j]); j--)
          temp = j;
        for (int k = temp; k < END && digit(text[k]); k++)
          stringBuilder.append(text[k]);
        values.add(Integer.parseInt(stringBuilder.toString()));
      }

      if (digit(text[peek[RIGHT]])) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int k = peek[RIGHT]; k < END && digit(text[k]); k++)
          stringBuilder.append(text[k]);
        values.add(Integer.parseInt(stringBuilder.toString()));
      }
    }

    return values.stream().mapToInt(Integer::intValue).sum();
  }

  static boolean symbol(char c) {
    return (int) c != 46 && ((int) c > 57 || (int) c < 48);
  }

  static boolean digit(char c) {
    return (int) c > 47 && (int) c < 58;
  }

  static boolean number(char[] c) {
    for (char i : c)
      if (!digit(i))
        return false;
    return true;
  }

  static int up(int i) {
    return i - WIDTH > 0 ? i - WIDTH : 0;
  }

  static int down(int i) {
    return i + WIDTH < END ? i + WIDTH : END - 1;
  }

  static int left(int i) {
    return i - 1 > 0 ? i - 1 : 0;
  }

  static int right(int i) {
    return i + 1 < END ? i + 1 : END - 1;
  }

  static int[] proximity(int i) {
    return new int[] { up(left(i)), up(i), up(right(i)), left(i), right(i), down(left(i)), down(i), down(right(i)) };
  }
}