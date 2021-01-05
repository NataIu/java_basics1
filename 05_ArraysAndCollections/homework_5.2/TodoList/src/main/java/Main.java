import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static TodoList todoList = new TodoList();

    public static void main(String[] args) {

        String writtenText = new String();

        while (true) {

            Scanner scanner = new Scanner(System.in);

            writtenText = scanner.nextLine();
            CommandDescription commandDescription = new CommandDescription(writtenText);
            if (commandDescription.getCommandType() == CommandType.LIST) {
                ArrayList myList = todoList.getTodos();
                for (int i = 0; i < myList.size(); i++) {
                    System.out.println("" + i + " - " + myList.get(i));
                }
            } else if (commandDescription.getCommandType() == CommandType.ADD_WITH_INDEX) {
                todoList.add(commandDescription.getCommandParam(), commandDescription.getCommandText());
            } else if (commandDescription.getCommandType() == CommandType.ADD) {
                todoList.add(commandDescription.getCommandText());
            } else if (commandDescription.getCommandType() == CommandType.EDIT) {
                todoList.edit(commandDescription.getCommandText(), commandDescription.getCommandParam());
            } else if (commandDescription.getCommandType() == CommandType.DELETE) {
                todoList.delete(commandDescription.getCommandParam());
            } else {
                break;
            }

        }
    }

}
