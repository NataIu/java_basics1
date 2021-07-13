package main.controller;

import main.TaskException;
import main.response.Task;
import main.response.TaskBook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    //получение списка дел
    @GetMapping("/tasks")
    public ResponseEntity list() {
        return new ResponseEntity(TaskBook.getTaskList(), HttpStatus.OK);
    }

    //получение дела по id
    @GetMapping("/tasks/{id}")
    public ResponseEntity getTaskById(@PathVariable Integer id) {
        Task task = TaskBook.getTaskById(id);
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
            Integer result = (TaskBook.addTask(task));
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
            Task result = (TaskBook.updateTask(task));
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
        return new ResponseEntity(TaskBook.deleteTask(id), HttpStatus.OK);
    }


    //удаление всего списка
    @DeleteMapping("/tasks")
    public ResponseEntity deleteAllTask() {
        TaskBook.deleteAllTasks();
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
