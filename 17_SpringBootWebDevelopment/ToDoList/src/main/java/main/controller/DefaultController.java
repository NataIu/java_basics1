package main.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class DefaultController {

    @GetMapping ("/")
    public String index(@RequestParam String someText, @RequestParam int value) {
        StringBuilder resultString = new StringBuilder();
        // for test: http://localhost:8080/?someText=stuff&value=3
        for (int i = 0; i < value; i++) {
            resultString.append(someText);
        }
        return resultString.toString();
    }
}
