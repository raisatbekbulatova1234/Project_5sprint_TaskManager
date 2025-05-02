import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
        subtaskHashMap.clear();
    }

    public void deleteAllSubtasks() {
        for (Epic epic : epicHashMap.values()) {
            updateEpic(new Epic(epic.getTitle(), epic.getDescription(), epic.getListSubtask(), StatusOfTask.DONE));
            subtaskHashMap.clear();
        }
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
        task.setId(counterOfTasks);
        taskHashMap.put(task.getId(), task);

    }

    public void createNewEpic(Epic epic) {
        epic.setId(counterOfTasks);
        epicHashMap.put(epic.getId(), epic);

    }

    public void createNewSubtask(Subtask subtask) {
        subtask.setId(counterOfTasks);
        subtaskHashMap.put(subtask.getId(), subtask);
        int epicId = subtask.getEpicId();
        Epic epic = epicHashMap.get(epicId);
        epic.getListSubtask().add(subtask.getId());
        updateEpic(new Epic(epic.getTitle(), epic.getDescription(), epic.getListSubtask(), StatusOfTask.IN_PROGRESS));
    }

    public void updateTask(Task task) {
        taskHashMap.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        epicHashMap.put(epic.getId(), epic);
    }

    public void updateSubtask(Subtask subtask) {
        subtaskHashMap.put(subtask.getId(), subtask);
        int epicId = subtask.getEpicId();
        Epic epic = epicHashMap.get(epicId);
        epic.getListSubtask().add(subtask.getId());
        updateEpic(new Epic(epic.getTitle(), epic.getDescription(), epic.getListSubtask(), StatusOfTask.IN_PROGRESS));
    }

    public void deleteTaskById(int id) {
        taskHashMap.remove(id);
    }

    public void deleteEpicById(int id) {
        epicHashMap.remove(id);
        for (Subtask subtask : subtaskHashMap.values()) {
            if (subtask.getEpicId() == id) {
                subtaskHashMap.remove(subtask.getId());
            }
        }

    }

    public void deleteSubtaskById(int id) {
        int epicId = subtaskHashMap.get(id).getEpicId();
        Epic epic = epicHashMap.get(epicId);
        epic.getListSubtask().remove(id);
        updateEpic(new Epic(epic.getTitle(), epic.getDescription(), epic.getListSubtask(), StatusOfTask.IN_PROGRESS));
        subtaskHashMap.remove(id);

    }

    public List<Subtask> getAllSubtaskFromEpic(int epicId) {
        System.out.println("Вот список всех позадач Эпика " + epicHashMap.get(epicId) + " : \n");
        List<Integer> idListSubtask = epicHashMap.get(epicId).getListSubtask();
        List<Subtask> subtasksFromEpic = new ArrayList<>();
        for (Integer id : idListSubtask) {
            subtasksFromEpic.add(subtaskHashMap.get(id));
        }
        return subtasksFromEpic;
    }

}

