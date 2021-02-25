import lombok.Getter;

public class IncorrectFieldsQuantityException extends Exception {

    @Getter
    String text;
    int expected;

    IncorrectFieldsQuantityException(String email, int expected) {
        this.text = email;
        this.expected = expected;
    }

    @Override
    public String getMessage() {
        return "Wrong quantity of fields in string \""+text+"\". Correct quantity: "+expected;
    }

}
