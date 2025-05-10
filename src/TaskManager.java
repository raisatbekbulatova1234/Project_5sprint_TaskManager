import java.util.ArrayList;
import java.util.HashMap;

public interface TaskManager {
    void setCounterOfTasks();

    HashMap<Integer, Epic> getEpicHashMap();

    HashMap<Integer, Task> getTaskHashMap();

    HashMap<Integer, Subtask> getSubtaskHashMap();

    int getCounterOfTasks();

    ArrayList<Task> getAllTasks();

    ArrayList<Epic> getAllEpics();

    ArrayList<Subtask> getAllSubtasks();

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubtasks();

    Task getTaskById(int id);

    Task getEpicById(int id);

    Task getSubtaskById(int id);

    void createNewTask(Task task);

    void createNewEpic(Epic epic);

    void createNewSubtask(Subtask subtask);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(Subtask subtask);

    void deleteTaskById(int id);

    void deleteEpicById(int id);

    void deleteSubtaskById(int id);

    ArrayList<Subtask> getAllSubtaskFromEpic(int epicId);

    void updateEpicStatus(int epicId);

    void counterOfCallTasks(Task task);

    ArrayList<Task> getHistory();
}
