package main.service;

import main.TaskException;
import main.response.Task;
import main.response.TaskBook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class TaskService implements TaskServiceImpl{

    @Override
    public ResponseEntity list() {
        return new ResponseEntity(TaskBook.getTaskList(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getTaskById(Integer id) {
        Task task = TaskBook.getTaskById(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @Override
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


    @Override
    public ResponseEntity updateTask(Integer id, Task task) {
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

    @Override
    public ResponseEntity deleteTask(@PathVariable Integer id) {
        return new ResponseEntity(TaskBook.deleteTask(id), HttpStatus.OK);
    }


   @Override
    public ResponseEntity deleteAllTask() {
        TaskBook.deleteAllTasks();
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
