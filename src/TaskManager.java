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
        subtaskHashMap.clear();
    }

    public void deleteAllSubtasks() {
        for (Epic epic : epicHashMap.values()) {
            //updateEpic(new Epic(epic.getTitle(), epic.getDescription(), epic.getListSubtask(), StatusOfTask.DONE));
            subtaskHashMap.clear();
            epic.getListSubtask().clear();
            updateEpicStatus(epic.getId());
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
        //updateEpic(new Epic(epic.getTitle(), epic.getDescription(), epic.getListSubtask(), StatusOfTask.IN_PROGRESS));
        updateEpicStatus(epicId);
    }

    public void updateTask(Task task) {
        if (taskHashMap.containsKey(task.getId())) {
            taskHashMap.put(task.getId(), task);
        } else {
            System.out.println("Такой задачи не существует! Проверь еще раз)");
        }
    }

    public void updateEpic(Epic epic) {
        if (epicHashMap.containsKey(epic.getId())) {
            epicHashMap.put(epic.getId(), epic);
            System.out.println("Такого эпика не существует! Проверь еще раз)");

        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtaskHashMap.containsKey(subtask.getId())) {
            subtaskHashMap.put(subtask.getId(), subtask);
        } else {
            System.out.println("Такой подзадачи не существует! Проверь еще раз)");
            return;
        }
        int epicId = subtask.getEpicId();
        Epic epic = epicHashMap.get(epicId);
        epic.getListSubtask().add(subtask.getId());
        //updateEpic(new Epic(epic.getTitle(), epic.getDescription(), epic.getListSubtask(), StatusOfTask.IN_PROGRESS));
        updateEpicStatus(epicId);
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
        //updateEpic(new Epic(epic.getTitle(), epic.getDescription(), epic.getListSubtask(), StatusOfTask.IN_PROGRESS));
        updateEpicStatus(epicId);
        subtaskHashMap.remove(id);

    }

    public void getAllSubtaskFromEpic(int epicId) {
        System.out.println("Вот список всех позадач Эпика '" + epicHashMap.get(epicId).getTitle() + "' : \n");
        for (Integer id : epicHashMap.get(epicId).getListSubtask()) {
           Subtask subtask = subtaskHashMap.get(id);
            System.out.println(subtask);
        }
    }

    public void updateEpicStatus(int epicId) {
        if (epicHashMap.containsKey(epicId)) {
            Epic epic = epicHashMap.get(epicId);
            for (Integer id : epic.getListSubtask()) {
                for (Subtask subtask : subtaskHashMap.values()) {
                    if ((subtask.getId() == id) && subtask.getStatusOfTask().equals(StatusOfTask.DONE)) {
                        updateEpic(new Epic(epic.getTitle(), epic.getDescription(), epic.getListSubtask(), StatusOfTask.DONE));

                    } else if ((subtask.getStatusOfTask().equals(StatusOfTask.NEW) || epic.getListSubtask().isEmpty())) {
                        updateEpic(new Epic(epic.getTitle(), epic.getDescription(), epic.getListSubtask(), StatusOfTask.NEW));

                    } else
                        updateEpic(new Epic(epic.getTitle(), epic.getDescription(), epic.getListSubtask(), StatusOfTask.IN_PROGRESS));

                }

            }
        }
    }

}

