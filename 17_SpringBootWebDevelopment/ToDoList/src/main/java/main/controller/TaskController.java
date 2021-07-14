package main.controller;

import main.TaskException;
import main.response.Task;
import main.response.TaskBook;
import main.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @Autowired
    TaskServiceImpl taskService;

    //получение списка дел
    @GetMapping("/tasks")
    public ResponseEntity list() {
        return taskService.list();
    }

    //получение дела по id
    @GetMapping("/tasks/{id}")
    public ResponseEntity getTaskById(@PathVariable Integer id) {
        return taskService.getTaskById(id);
    }

    //создание дела
    @PostMapping("/tasks")
    public ResponseEntity addTask(Task task) {
        return taskService.addTask(task);
    }


    //обновление дела
    @PutMapping("/tasks/{id}")
    public ResponseEntity updateTask(@PathVariable Integer id, Task task) {
        return taskService.updateTask(id,task);

    }

    //удаление дела
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable Integer id) {
        return taskService.deleteTask(id);
    }


    //удаление всего списка
    @DeleteMapping("/tasks")
    public ResponseEntity deleteAllTask() {
        return taskService.deleteAllTask();
    }
}
