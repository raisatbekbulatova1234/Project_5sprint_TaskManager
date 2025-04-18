import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    public static int counterOfTasks = 1;

    HashMap<Integer, Task> taskHashMap = new HashMap<>();
    HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();

    public ArrayList<Task> getAllTasks() {
        System.out.println("Вот список всех задач :");
        return new ArrayList<>(taskHashMap.values());
    }

    public void deleteAllTasks() {
        taskHashMap.clear();
    }

    public Task getTaskById(int id) {
        return taskHashMap.getOrDefault(id, null);
    }

    public void createNewTask(Task task) {
        taskHashMap.put(task.getId(), task);

    }

    public void updateTask(Task task) {
        taskHashMap.put(task.getId(), task);
    }

    public void deleteById(int id) {
        taskHashMap.remove(id);
    }
}

