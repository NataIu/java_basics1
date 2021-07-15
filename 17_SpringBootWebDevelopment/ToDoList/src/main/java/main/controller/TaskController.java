package main.controller;

import main.TaskException;
import main.model.Task;
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
        return new ResponseEntity(taskService.list(), HttpStatus.OK);
    }

    //получение дела по id
    @GetMapping("/tasks/{id}")
    public ResponseEntity getTaskById(@PathVariable Integer id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    //создание дела
    @PostMapping("/tasks")
    public ResponseEntity addTask(Task task) {
        ResponseEntity response;
        try {
            Integer result = taskService.addTask(task);
            response = new ResponseEntity(result, HttpStatus.OK);
        }
        catch (TaskException e) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
        return response;
    }


    //обновление дела
    @PutMapping("/tasks/{id}")
    public ResponseEntity updateTask(@PathVariable Integer id, Task task) {
        ResponseEntity response;
        try {
            Task result = taskService.updateTask(id,task);
            response = new ResponseEntity(result, HttpStatus.OK);
        }
        catch (TaskException e) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
        return response;
    }

    //удаление дела
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable Integer id) {
        return new ResponseEntity(taskService.deleteTask(id), HttpStatus.OK);
    }


    //удаление всего списка
    @DeleteMapping("/tasks")
    public ResponseEntity deleteAllTask() {
        taskService.deleteAllTask();
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
