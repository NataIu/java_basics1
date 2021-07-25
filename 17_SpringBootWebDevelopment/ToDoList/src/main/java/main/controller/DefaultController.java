package main.controller;

import main.model.Task;
import main.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class DefaultController {

    @Autowired
    TaskServiceImpl taskService;

//    @GetMapping ("/")
//    public String index(@RequestParam String someText, @RequestParam int value) {
//        StringBuilder resultString = new StringBuilder();
//        // for test: http://localhost:8080/?someText=stuff&value=3
//        for (int i = 0; i < value; i++) {
//            resultString.append(someText);
//        }
//        return resultString.toString();
//    }

    @RequestMapping ("/")
    public String index(Model model) {
        List<Task> list = taskService.list();
        model.addAttribute("tasks", list);
        return "index";
    }
}
