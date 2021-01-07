import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static final String WRONG_EMAIL_ANSWER = "Неверный формат email";
    private static final int emailStartNumber = 4;


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EmailList emailList = new EmailList();

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            if (input.equals("LIST")) {

                List<String> listForPrinting = emailList.getSortedEmails();
                for (String email : listForPrinting) {
                    System.out.println(email);
                }

            } else if (input.matches("ADD\\s.*")) {
                String pureEmail = input.substring(emailStartNumber);
                emailList.add(pureEmail);
            }

            //TODO: write code here

        }
    }
}
