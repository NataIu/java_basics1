import lombok.Getter;
import lombok.Setter;

public class IncorrectEmailException extends Exception{

@Getter
@Setter
String email;

    IncorrectEmailException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return "Wrong e-mail format (\""+email+"\"). Correct format: vasily.petrov@gmail.com";
    }

}
