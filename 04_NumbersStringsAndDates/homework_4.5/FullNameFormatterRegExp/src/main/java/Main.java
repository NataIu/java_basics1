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
      System.out.println("Отчество: " + parts[2]);
    }
  }

  private static boolean isWrongFormat(String text) {
    String wordFormat = "[а-яА-ЯёЁ-]+";
    String spaceFormat = " +";
    return !text.matches(wordFormat+spaceFormat+wordFormat+spaceFormat+wordFormat);
  }

}