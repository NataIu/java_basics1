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

      if (input.replaceAll("[^а-яА-Я \\-]","").length() != input.length()) {
        System.out.println(FORMAT_ERROR_MESSAGE);
        continue;
      }

      String[] parts = input.trim().split("\\s+");
      if (parts.length != 3) {
        System.out.println(FORMAT_ERROR_MESSAGE);
        continue;
      }

      System.out.println("Фамилия: " + parts[0]);
      System.out.println("Имя: " + parts[1]);
      System.out.println("Отчество: " + parts[2]);


    }
  }

}