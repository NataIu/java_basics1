package main.service;

import main.TaskException;
import main.model.Task;

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
    public void deleteTask(Integer id);

    //удаление всего списка
    public void deleteAllTask() ;
}
