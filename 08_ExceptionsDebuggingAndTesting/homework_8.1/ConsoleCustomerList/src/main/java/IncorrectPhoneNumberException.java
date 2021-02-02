import lombok.Getter;
import lombok.Setter;

public class IncorrectPhoneNumberException extends Exception{

    @Getter
    @Setter
    String phoneNumber;

    IncorrectPhoneNumberException(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getMessage() {
        return "Wrong mobile phone format (\""+phoneNumber+"\"). Correct format: +79215637722";
    }


}
