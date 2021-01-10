import java.util.Scanner;
import java.util.Set;

public class Main {

    private final static String WRONG_INPUT_MESSAGE = "Неверный формат ввода";
    private final static String START_MESSAGE = "Введите номер, имя или команду:";
    private final static String SUCCESS_SAVING_MESSAGE = "Контакт сохранен!";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();


        while (true) {
            System.out.println(START_MESSAGE);
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            InformationType inputType = InformationType.findInformationType(input);
            if (inputType == InformationType.LIST) {
                Set<String> allContacts = phoneBook.getAllContacts();
                printContacts(allContacts);
            } else if (inputType == InformationType.NAME) {
                processNameInput(scanner, phoneBook, input);
            } else if (inputType == InformationType.PHONE_NUMBER) {
                processPhoneInput(scanner, phoneBook, input);
            } else {
                System.out.println(WRONG_INPUT_MESSAGE);
            }

        }
    }

    private static void processNameInput(Scanner scanner, PhoneBook phoneBook, String input) {
        Set<String> phones = phoneBook.getPhonesByName(input);
        if (phones.isEmpty()) {
            System.out.println("Такого имени в телефонной книге нет.\n" +
                    "Введите номер телефона для абонента \"" + input + "\":");
            String additionalInput = scanner.nextLine();
            if (InformationType.findInformationType(additionalInput) == InformationType.PHONE_NUMBER) {
                phoneBook.addContact(additionalInput, input);
                System.out.println(SUCCESS_SAVING_MESSAGE);
            } else {
                System.out.println(WRONG_INPUT_MESSAGE);
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
            if (InformationType.findInformationType(additionalInput) == InformationType.NAME) {
                phoneBook.addContact(input, additionalInput);
                System.out.println(SUCCESS_SAVING_MESSAGE);
            } else {
                System.out.println(WRONG_INPUT_MESSAGE);
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


}
