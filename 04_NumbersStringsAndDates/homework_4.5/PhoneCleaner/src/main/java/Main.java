import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            input = input.replaceAll("[ //(//)-//+]", "").trim();
            if (input.matches("[78]?[0-9]{10}")) {
                if (input.length() == 11) {
                  input = input.substring(1);
                }
                System.out.println("7" + input);
            } else {
                System.out.println("Неверный формат номера");
            }
        }
    }

}
