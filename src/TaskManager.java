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
        System.out.println("Список задач очищен!");
    }

    public Task getTaskById(int id) {
        if (taskHashMap.containsKey(id)) {
            System.out.print("Задача найдена : ");
            return taskHashMap.get(id);
        } else {
            System.out.print("Задача не найдена : ");
            return null;
        }
    }

    public void createNewTask(Task task) {
        taskHashMap.put(task.getId(), task);
        System.out.println("Задача успешно создана!");

    }

    public void updateTask(Task task) {
        taskHashMap.put(task.getId(), task);
        System.out.println("Задача успешно обновлена!");

    }

    public void deleteById(int id) {
        taskHashMap.remove(id);
        System.out.println("Задача успешно удалена!");
    }
}

