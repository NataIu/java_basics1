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

    boolean textIsGood = true;

    //количество слов 3-4 (учитываем двойные отчества «Ахмед оглы»)
    textIsGood = text.matches("([А-Яа-яЁё-]+ ){2}[А-Яа-яЁё-]+( [А-Яа-яЁё-]*)?");
    //первые три слова - с большой буквы (двойные имена и фамилии - вторая часть тоже с большой буквы, например Салтыков-Щедрин)
    textIsGood = textIsGood && text.matches("([А-ЯЁ][а-яё]*[- ]){2}[А-ЯЁ].*");
    //все остальные буквы - маленькие
    textIsGood = textIsGood && text.matches("([А-ЯЁ][^А-ЯЁ]*){2}[А-ЯЁ][^А-ЯЁ]*");
    //нет знаков препинания и прочих символов, за исключением тире (двойные имена и фамилии могут содержать)
    textIsGood = textIsGood && text.matches("[ А-ЯЁа-яЁё-]*");

    return !textIsGood;

  }

}