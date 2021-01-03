import java.util.Scanner;

public class Main {

  private static final String FORMAT_ERROR_MESSAGE = "Введенная строка не является ФИО";

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }



      if (isWrongFormat(input)) {
        System.out.println(FORMAT_ERROR_MESSAGE);
        continue;
      }

      String[] parts = input.trim().split("\\s+");
      System.out.println("Фамилия: " + parts[0]);
      System.out.println("Имя: " + parts[1]);
      System.out.println("Отчество: " + parts[2]+ (parts.length == 3 ? "": " "+parts[3]));
    }
  }

  private static boolean isWrongFormat(String text) {

    String pattern1 = "[А-ЯЁ][а-яё]*"; //lastNameFormatPartOne, nameFormatPartOne, secondNameFormatPartOne
    String pattern2 = "(-[А-ЯЁ][а-яё]*)?"; //lastNameFormatPartTwo, nameFormatPartTwo,
    String pattern3 ="( [а-яё]*)?"; //secondNameFormatPartOne

    String commonPattern = pattern1 + pattern2 + " " + pattern1 + pattern2 + " "+pattern1 + pattern3;


    return !text.matches(commonPattern);
  }

}