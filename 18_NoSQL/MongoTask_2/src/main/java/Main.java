
import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args) {

        MongoBase mongoBase = new MongoBase();
        mongoBase.init();

        scanner = new Scanner(System.in);
        for (; ; ) {
            try {
                readAndExecuteCommand(mongoBase);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private static void readAndExecuteCommand(MongoBase mongoBase) throws ShopException {
        System.out.println("Введите команду");
        String line = scanner.nextLine().trim();

        Command command = new Command();
        command.init(line);

        mongoBase.executeCommand(command);
    }
}
