import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    public static int counterOfTasks = 1;

    HashMap<Integer, Task> taskHashMap = new HashMap<>();
    HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(taskHashMap.values());
    }

    public void deleteAllTasks() {
        taskHashMap.clear();
        System.out.println("Список задач пуст!");
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
        Task newTask = new Task(task.getTitle(), task.getDescription());
        taskHashMap.put(newTask.getId(), newTask);
    }

    public void updateTask(Task task) {
        taskHashMap.put(task.getId(), task);
    }

    public void deleteById(int id) {

        for (Integer task : taskHashMap.keySet()) {
            if (id == task) {
                taskHashMap.remove(task);
            }
        }
    }
}

