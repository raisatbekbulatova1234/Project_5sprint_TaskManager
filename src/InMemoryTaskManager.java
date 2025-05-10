import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private int counterOfTasks = 0;

    private HashMap<Integer, Task> taskHashMap = new HashMap<>();
    private HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    private HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();

    private ArrayList<Task> viewedTasks = new ArrayList<>();

    @Override
    public void setCounterOfTasks() {
        this.counterOfTasks++;
    }

    @Override
    public HashMap<Integer, Epic> getEpicHashMap() {
        return epicHashMap;
    }

    @Override
    public HashMap<Integer, Task> getTaskHashMap() {
        return taskHashMap;
    }

    @Override
    public HashMap<Integer, Subtask> getSubtaskHashMap() {
        return subtaskHashMap;
    }

    @Override
    public int getCounterOfTasks() {
        return counterOfTasks;
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        System.out.println("Вот список всех задач :");
        return new ArrayList<>(taskHashMap.values());
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        System.out.println("Вот список всех эпиков :");
        return new ArrayList<>(epicHashMap.values());
    }

    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        System.out.println("Вот список всех позадач :");
        return new ArrayList<>(subtaskHashMap.values());
    }

    @Override
    public void deleteAllTasks() {
        taskHashMap.clear();
    }

    @Override
    public void deleteAllEpics() {
        epicHashMap.clear();
        subtaskHashMap.clear();
    }

    @Override
    public void deleteAllSubtasks() {
        for (Epic epic : epicHashMap.values()) {
            epic.getListSubtask().clear();
            updateEpicStatus(epic.getId());
            subtaskHashMap.clear();
        }
    }

    @Override
    public Task getTaskById(int id) {
        counterOfCallTasks(taskHashMap.get(id));
        return taskHashMap.getOrDefault(id, null);
    }

    @Override
    public Task getEpicById(int id) {
        counterOfCallTasks(epicHashMap.get(id));
        return epicHashMap.getOrDefault(id, null);
    }

    @Override
    public Task getSubtaskById(int id) {
        counterOfCallTasks(subtaskHashMap.get(id));
        return subtaskHashMap.getOrDefault(id, null);
    }


    @Override
    public void createNewTask(Task task) {
        task.setId(counterOfTasks);
        taskHashMap.put(task.getId(), task);

    }

    @Override
    public void createNewEpic(Epic epic) {
        epic.setId(epic.getId());
        epicHashMap.put(epic.getId(), epic);
    }

    @Override
    public void createNewSubtask(Subtask subtask) {
        subtask.setId(counterOfTasks);
        subtaskHashMap.put(subtask.getId(), subtask);
        int epicId = subtask.getEpicId();
        Epic epic = epicHashMap.get(epicId);
        epic.getListSubtask().add(subtask.getId());
        updateEpicStatus(epicId);
    }

    @Override
    public void updateTask(Task task) {
        if (taskHashMap.containsKey(task.getId())) {
            taskHashMap.put(task.getId(), task);
        } else {
            System.out.println("Такой задачи не существует! Проверь еще раз)");
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epicHashMap.containsKey(epic.getId())) {
            epicHashMap.put(epic.getId(), epic);
            System.out.println("Такого эпика не существует! Проверь еще раз)");

        }
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        if (subtaskHashMap.containsKey(subtask.getId())) {
            subtaskHashMap.put(subtask.getId(), subtask);
        } else {
            System.out.println("Такой подзадачи не существует! Проверь еще раз)");
            return;
        }
        int epicId = subtask.getEpicId();
        updateEpicStatus(epicId);
    }

    @Override
    public void deleteTaskById(int id) {
        taskHashMap.remove(id);
    }

    @Override
    public void deleteEpicById(int id) {
        epicHashMap.remove(id);
        for (Subtask subtask : subtaskHashMap.values()) {
            if (subtask.getEpicId() == id) {
                subtaskHashMap.remove(subtask.getId());
            }
        }

    }

    @Override
    public void deleteSubtaskById(int id) {
        int epicId = subtaskHashMap.get(id).getEpicId();
        Epic epic = epicHashMap.get(epicId);
        epic.getListSubtask().remove((Integer) id);
        subtaskHashMap.remove(id);
        updateEpicStatus(epicId);

    }

    @Override
    public ArrayList<Subtask> getAllSubtaskFromEpic(int epicId) {
        ArrayList<Subtask> subtasks = new ArrayList<>();
        for (Integer id : epicHashMap.get(epicId).getListSubtask()) {
            Subtask subtask = subtaskHashMap.get(id);
            subtasks.add(subtask);
        }
        return subtasks;
    }

    @Override
    public void updateEpicStatus(int epicId) {
        int counterNew = 0;
        int counterDone = 0;

        Epic epic = epicHashMap.get(epicId);
        if (epic.getListSubtask().isEmpty()) {
            epic.setStatusOfTask(StatusOfTask.DONE);
        } else
            for (int idSubtaskFromEpicSubtasks : epic.getListSubtask()) {
                for (Subtask subtask : subtaskHashMap.values()) {

                    if (subtask.getId() == idSubtaskFromEpicSubtasks) {

                        if (subtask.getStatusOfTask().equals(StatusOfTask.DONE)) {
                            counterDone++;
                        } else if (subtask.getStatusOfTask().equals(StatusOfTask.NEW)) {
                            counterNew++;
                        }
                    } else
                        System.out.println("Что-то пошло не так!");

                }
                if (counterDone == epic.getListSubtask().size()) {
                    epic.setStatusOfTask(StatusOfTask.DONE);
                } else if (counterNew == epic.getListSubtask().size()) {
                    epic.setStatusOfTask(StatusOfTask.NEW);

                } else
                    epic.setStatusOfTask(StatusOfTask.IN_PROGRESS);

            }
    }

    public void counterOfCallTasks(Task task) {
        if (viewedTasks.size() >= 10) {
            viewedTasks.removeFirst();
            viewedTasks.add(task);
        } else
            viewedTasks.add(task);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return viewedTasks;
    }
}





