import lombok.Getter;
import lombok.Setter;

public class IllegalCommandException extends Exception {

    @Getter
    String commandName;

    IllegalCommandException(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public String getMessage() {

        String message;

        if (commandName.toLowerCase().equals("add")) {
            message = "Wrong format for command \"" + commandName + "\". Correct format: add Василий Петров " +
                    "vasily.petrov@gmail.com +79215637722";
        }
        else if (commandName.toLowerCase().equals("remove")) {
            message = "Wrong format for command \"" + commandName + "\". Correct format: remove Василий Петров";
        }
        else {
            message = super.getMessage();
        }

        return message;
    }
}
