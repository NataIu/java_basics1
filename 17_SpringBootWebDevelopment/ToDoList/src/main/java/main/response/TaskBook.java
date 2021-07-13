package main.response;

import lombok.Getter;
import main.TaskException;

import java.util.*;

public class TaskBook {

    private static  Map<Integer, Task> tasks = Collections.synchronizedMap(new HashMap<>());

    public static ArrayList<Task> getTaskList() {
        ArrayList<Task> list = new ArrayList<>();
        list.addAll(tasks.values());
        return list;
    }

    public static Task getTaskById(Integer id) {
        return tasks.get(id);
    }

    public static Integer addTask(Task task) throws TaskException{
        Integer result = -1;
        if (tasks.containsKey(task.getId())) {
            throw new TaskException("tTask with id = " + task.getId() + " already exists");
        }
        else {
            tasks.put(task.getId(),task);
            result = task.getId();
        }

        return result;
    }

    public static Task updateTask(Task task) throws TaskException {
        if (!tasks.containsKey(task.getId())) {
            throw new TaskException("Task with id = " + task.getId() + " do not exist");
        }
        else {
            tasks.remove(task.getId());
            tasks.put(task.getId(),task);
        }
        return task;
    }

    public static Task deleteTask(Integer id) {
       Task removedTask = tasks.remove(id);
        return removedTask;
    }

    public static void deleteAllTasks() {
        tasks.clear();
        return;
    }

}
