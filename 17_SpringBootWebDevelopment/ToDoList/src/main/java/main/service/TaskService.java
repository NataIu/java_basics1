package main.service;

import main.TaskException;
import main.response.Task;
import main.response.TaskBook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class TaskService implements TaskServiceImpl {

    @Override
    public List<Task> list() {
        return TaskBook.getTaskList();
    }

    @Override
    public Task getTaskById(Integer id) {
        return TaskBook.getTaskById(id);
    }

    @Override
    public Integer addTask(Task task) throws TaskException {
        return TaskBook.addTask(task);
    }


    @Override
    public Task updateTask(Integer id, Task task) throws TaskException {
        return TaskBook.updateTask(task);
    }

    @Override
    public Task deleteTask(Integer id) {
        return TaskBook.deleteTask(id);
    }


    @Override
    public void deleteAllTask() {
        TaskBook.deleteAllTasks();
    }
}
