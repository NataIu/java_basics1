import lombok.Getter;
import lombok.Setter;

public class IllegalCommandException extends Exception {

    @Getter
    @Setter
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
        } else {
            message = super.getMessage();
        }

        return message;
    }
}
