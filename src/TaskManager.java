import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    public int counterOfTasks = 1;

    HashMap<Integer, Task> taskHashMap = new HashMap<>();
    HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();

    public ArrayList<Task> getAllTasks() {
        System.out.println("Вот список всех задач :");
        return new ArrayList<>(taskHashMap.values());
    }

    public ArrayList<Epic> getAllEpics() {
        System.out.println("Вот список всех эпиков :");
        return new ArrayList<>(epicHashMap.values());
    }

    public ArrayList<Subtask> getAllSubtasks() {
        System.out.println("Вот список всех позадач :");
        return new ArrayList<>(subtaskHashMap.values());
    }

    public void deleteAllTasks() {
        taskHashMap.clear();
    }

    public void deleteAllEpics() {
        epicHashMap.clear();
    }

    public void deleteAllSubtasks() {
        subtaskHashMap.clear();
    }

    public Task getTaskById(int id) {
        return taskHashMap.getOrDefault(id, null);
    }

    public Task getEpicById(int id) {
        return epicHashMap.getOrDefault(id, null);
    }

    public Task getSubtaskById(int id) {
        return subtaskHashMap.getOrDefault(id, null);
    }


    public void createNewTask(Task task) {
        taskHashMap.put(task.getId(), task);

    }

    public void createNewEpic(Epic epic) {
        epicHashMap.put(epic.getId(), epic);

    }

    public void createNewSubtask(Subtask subtask) {
        subtaskHashMap.put(subtask.getId(), subtask);

    }

    public void updateTask(Task task) {
        taskHashMap.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        epicHashMap.put(epic.getId(), epic);
    }

    public void updateSubtask(Subtask subtask) {
        subtaskHashMap.put(subtask.getId(), subtask);
    }

    public void deleteTaskById(int id) {
        taskHashMap.remove(id);
    }

    public void deleteEpicById(int id) {
        taskHashMap.remove(id);
    }

    public void deleteSubtaskById(int id) {
        taskHashMap.remove(id);
    }

    public List<Subtask> getAllSubtaskFromEpic(int epicId) {
        System.out.println("Вот список всех позадач Эпика " + epicHashMap.get(epicId) + " :");
        List<Integer> idListSubtask = epicHashMap.get(epicId).getListSubtask();
        List<Subtask> subtasksFromEpic = new ArrayList<>();
        for (Integer id : idListSubtask) {
            subtasksFromEpic.add(subtaskHashMap.get(id));
        }
        return subtasksFromEpic;
    }


    public void taskMenu() {
        System.out.println("Интерфейс приложения: \n" +
                "1. Создать новую задачу \n" +
                "2. Вывести список всех задач на экран \n" +
                "3. Очистить список задач \n" +
                "4. Обновить задачу \n" +
                "5. Получить задачу по идентификатору \n" +
                "6. Удалить задачу по идентификатору \n" +
                "0. Завершить работу \n");
    }
    public void epicMenu() {
        System.out.println("Интерфейс приложения: \n" +
                "1. Создать новый эпик \n" +
                "2. Вывести список всех эпиков на экран \n" +
                "3. Очистить список эпиков \n" +
                "4. Обновить эпик \n" +
                "5. Получить эпик по идентификатору \n" +
                "6. Удалить эпик по идентификатору \n" +
                "7. Получить список всех подзадач \n" +
                "0. Завершить работу \n");
    }
    public void subtaskMenu() {
        System.out.println("Интерфейс приложения: \n" +
                "1. Создать новую подзадачу \n" +
                "2. Вывести список всех подзадач на экран \n" +
                "3. Очистить список подзадач \n" +
                "4. Обновить подзадачу \n" +
                "5. Получить подзадачу по идентификатору \n" +
                "6. Удалить подзадачу по идентификатору \n" +
                "0. Завершить работу \n");
    }

}

