package main;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskException extends Exception{

    String text;

    @Override
    public String getMessage() {
        return text;
    }
}
