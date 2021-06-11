import lombok.Getter;
import lombok.Setter;

public class BankOperationException extends Exception{

    @Getter
    String messageText;

    BankOperationException(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public String getMessage() {
        return messageText;
    }


}
