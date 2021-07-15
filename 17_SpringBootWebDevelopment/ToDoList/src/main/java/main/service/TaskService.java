package main.service;

import main.TaskException;
import main.model.Task;
import main.model.TaskRepository;
//import main.response.TaskBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements TaskServiceImpl {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> list() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> taskList = new ArrayList<>();
        for (Task task: taskIterable) {
            taskList.add(task);
        }
        return taskList;
    }

    @Override
    public Task getTaskById(Integer id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return task.get();
        }
        else {
            return null;
        }
    }

    @Override
    public Integer addTask(Task task) throws TaskException {
        Integer result = -1;
        if (taskRepository.findById(task.getId()).isPresent()) {
            throw new TaskException("tTask with id = " + task.getId() + " already exists");
        }
        else {
            taskRepository.save(task);
            result = task.getId();
        }

        return result;
    }


    @Override
    public Task updateTask(Integer id, Task task) throws TaskException {

        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new TaskException("Task with id = " + id + " do not exist");
        }
        else {
            taskRepository.deleteById(id);
            taskRepository.save(task);
        }
        return task;

    }

    @Override
    public Task deleteTask(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.deleteById(id);
            return optionalTask.get();
        }
        else {
            return null;
        }
    }


    @Override
    public void deleteAllTask() {
        taskRepository.deleteAll();
    }
}
