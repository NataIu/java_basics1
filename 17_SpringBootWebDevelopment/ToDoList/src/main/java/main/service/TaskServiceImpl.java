package main.service;

import main.TaskException;
import main.response.Task;
import main.response.TaskBook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface TaskServiceImpl {


    //получение списка дел
    public ResponseEntity list();

    //получение дела по id
    public ResponseEntity getTaskById(Integer id);

    //создание дела
    public ResponseEntity addTask(Task task);

    //обновление дела
    public ResponseEntity updateTask(Integer id, Task task);

    //удаление дела
    public ResponseEntity deleteTask(Integer id);

    //удаление всего списка
    public ResponseEntity deleteAllTask() ;
}
