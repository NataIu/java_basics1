import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    private final static String wrongInputMessage = "Неверный формат ввода";
    private final static String startMessage = "Введите номер, имя или команду:";
    private final static String successSavingMessage = "Контакт сохранен!";
    public final static String NAME_PATTERN = "[А-ЯЁ][а-яё]*";
    public final static String PHONE_PATTERN = "79[0-9]{9}";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();


        while (true) {
            System.out.println(startMessage);
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            InformationType inputType = findInformationType(input);
            if (inputType == InformationType.LIST) {
                Set<String> allContacts = phoneBook.getAllContacts();
                printContacts(allContacts);
            } else if (inputType == InformationType.NAME) {
                processNameInput(scanner, phoneBook, input);
            } else if (inputType == InformationType.PHONE_NUMBER) {
                processPhoneInput(scanner, phoneBook, input);
            } else {
                System.out.println(wrongInputMessage);
            }

        }
    }

    private static void processNameInput(Scanner scanner, PhoneBook phoneBook, String input) {
        Set<String> phones = phoneBook.getPhonesByName(input);
        if (phones.isEmpty()) {
            System.out.println("Такого имени в телефонной книге нет.\n" +
                    "Введите номер телефона для абонента \"" + input + "\":");
            String additionalInput = scanner.nextLine();
            if (findInformationType(additionalInput) == InformationType.PHONE_NUMBER) {
                phoneBook.addContact(additionalInput,input);
                System.out.println(successSavingMessage);
            } else {
                System.out.println(wrongInputMessage);
            }

        } else {
            printContacts(phones);
        }
    }

    private static void processPhoneInput(Scanner scanner, PhoneBook phoneBook, String input) {
        String name = phoneBook.getNameByPhone(input);
        if (name.isEmpty()) {
            System.out.println("Такого номера нет в телефонной книге.\n" +
                    "Введите имя абонента для номера \"" + input + "\":");

            String additionalInput = scanner.nextLine();
            if (findInformationType(additionalInput) == InformationType.NAME) {
                phoneBook.addContact(input,additionalInput);
                System.out.println(successSavingMessage);
            } else {
                System.out.println(wrongInputMessage);
            }
        } else {
            System.out.println(name);
        }
    }

    private static void printContacts(Set<String> allContacts) {
        for (String contact : allContacts) {
            System.out.println(contact);
        }
    }

    private static InformationType findInformationType(String text) {

        InformationType result = InformationType.ANOTHER;

        if (text.equals("LIST")) {
            result = InformationType.LIST;
        } else if (text.matches(NAME_PATTERN)) {
            result = InformationType.NAME;
        } else if (text.matches(PHONE_PATTERN)) {
            result = InformationType.PHONE_NUMBER;
        }

        return result;
    }


}
