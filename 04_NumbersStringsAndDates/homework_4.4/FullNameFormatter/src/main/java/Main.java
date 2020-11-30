import java.util.Scanner;

public class Main {

//    private static final String VALID_SYMBOLS = "АБВГДЕЁЖЗИЙУЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя-";
    private static final String FORMAT_ERROR_MESSAGE = "Введенная строка не является ФИО";

    public static void main(String[] args) {

        String surname;
        String name;
        String middleName;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            input = input.trim();

            //проверим, что есть хотя бы два разных пробела (т.е. >=3 слов):
            if (input.indexOf(' ') == 0 || input.indexOf(' ') == input.lastIndexOf(' ')) {
                System.out.println(FORMAT_ERROR_MESSAGE);
                continue;
            }

            surname = input.substring(0, input.indexOf(' '));
            name = input.substring(input.indexOf(' ') + 1, input.lastIndexOf(' '));
            middleName = input.substring(input.lastIndexOf(' ') + 1);

            if (name.indexOf(' ') > 0) {
                //было больше 3 слов
                System.out.println(FORMAT_ERROR_MESSAGE);
                continue;
            }

            if (!isValidWord(surname) || !isValidWord(name) || !isValidWord(middleName)) {
                System.out.println(FORMAT_ERROR_MESSAGE);
                continue;
            }

            System.out.println("Фамилия: " + surname);
            System.out.println("Имя: " + name);
            System.out.println("Отчество: " + middleName);
        }

    }


    private static boolean isValidWord(String text) {

        boolean isValidSymbol = true;
        for (int i = 0; i < text.length(); i++) {
            if ( text.charAt(i) != '-' &&
                    ((Character.toLowerCase(text.charAt(i)) > 'я') || (Character.toLowerCase(text.charAt(i)) < 'а'))) {
                isValidSymbol = false;
                break;
            }
        }
        return isValidSymbol;
    }

}