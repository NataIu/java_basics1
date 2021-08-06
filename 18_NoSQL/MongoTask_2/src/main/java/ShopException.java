import lombok.Getter;
import lombok.Setter;

public class ShopException extends Exception {

    @Getter
    String message;

    ShopException(String message) {
        this.message = message;
    }

}
