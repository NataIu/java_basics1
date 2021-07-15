package main.service;

import main.TaskException;
import main.response.Task;
import main.response.TaskBook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TaskServiceImpl {


    //получение списка дел
    public List<Task> list();

    //получение дела по id
    public Task getTaskById(Integer id);

    //создание дела
    public Integer addTask(Task task) throws TaskException;

    //обновление дела
    public Task updateTask(Integer id, Task task) throws TaskException;

    //удаление дела
    public Task deleteTask(Integer id);

    //удаление всего списка
    public void deleteAllTask() ;
}
