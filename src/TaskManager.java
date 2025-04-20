import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    public int counterOfTasks = 1;
    Scanner scanner = new Scanner(System.in);

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
        epicHashMap.remove(id);
    }

    public void deleteSubtaskById(int id) {
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

    public void taskApp(int command) {
        switch (command) {
            case 0:
                return;
            case 1:
                System.out.println("Введите название задачи :");
                String titleTask = scanner.nextLine();

                System.out.println("Введите описание задачи :");
                String descriptionTask = scanner.nextLine();

                createNewTask(new Task(titleTask, descriptionTask, StatusOfTask.NEW));
                break;
            case 2:
                int counter = 1;
                for (Task task : getAllTasks()) {
                    System.out.println(counter + ". " + task);
                    counter++;
                }
                break;
            case 3:
                deleteAllTasks();
                System.out.println("Список задач пуст!");
                break;
            case 4:
                System.out.println("Введите id задачи, которую хотите обновить:");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Введите описание задачи :");
                String newDescriptionTask = scanner.nextLine();

                Task task = getTaskById(id);
                updateTask(new Task(task.getTitle(), newDescriptionTask, StatusOfTask.IN_PROGRESS));
                break;
            case 5:
                System.out.println("Введите id задачи, которую хотите найти:");
                int idTask = scanner.nextInt();
                System.out.println(getTaskById(idTask));
                break;
            case 6:
                System.out.println("Введите id задачи, которую хотите удалить:");
                int idTask1 = scanner.nextInt();
                deleteTaskById(idTask1);
                break;
        }

    }

    public void epicApp(int command) {
        switch (command) {
            case 0:
                return;
            case 1:
                System.out.println("Введите название эпика :");
                String epicTitle = scanner.nextLine();
                System.out.println("Введите описание эпика :");
                String descriptionOfEpic = scanner.nextLine();

                System.out.println("Введите название подзадачи :");
                String titleSubtask = scanner.nextLine();

                System.out.println("Введите описание подзадачи :");
                String descriptionSubtask = scanner.nextLine();
                int epicId = counterOfTasks++;
                Subtask subtask = new Subtask(titleSubtask, descriptionSubtask, epicId, StatusOfTask.NEW);

                List<Integer> integerListSubtask = new ArrayList<>();
                integerListSubtask.add(subtask.getId());
                createNewEpic(new Epic(epicTitle, descriptionOfEpic, integerListSubtask, StatusOfTask.NEW));
                break;
            case 2:
                int counter = 1;
                for (Epic epic : getAllEpics()) {
                    System.out.println(counter + ". " + epic);
                    counter++;
                }
                break;
            case 3:
                deleteAllEpics();
                System.out.println("Список эпиков пуст!");
                break;
            case 4:
                System.out.println("Введите id эпика, который хотите обновить:");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Введите описание эпика :");
                String newDescriptionTask = scanner.nextLine();
                List<Integer> integerList = epicHashMap.get(id).getListSubtask();

                Epic epic = new Epic(epicHashMap.get(id).getTitle(), newDescriptionTask, integerList, StatusOfTask.IN_PROGRESS);
                updateEpic(epic);
                break;
            case 5:
                System.out.println("Введите id эпика, для которого добавляем подзадачу :");
                int idDesiredTask = scanner.nextInt();
                System.out.println("Введите название подзадачи :");
                String newTitleSubtask = scanner.nextLine();
                scanner.nextLine();

                System.out.println("Введите описание подзадачи :");
                String newDescriptionSubtask = scanner.nextLine();
                Subtask subtask1 = new Subtask(newTitleSubtask, newDescriptionSubtask, idDesiredTask, StatusOfTask.IN_PROGRESS);
                subtaskHashMap.put(subtask1.getId(), subtask1);
                epicHashMap.get(idDesiredTask).getListSubtask().add(subtask1.getId());
                break;
            case 6:
                System.out.println("Введите id эпика, который хотите найти:");
                int idTask = scanner.nextInt();
                System.out.println(getTaskById(idTask));
                break;
            case 7:
                System.out.println("Введите id эпика, который хотите удалить:");
                int idTask1 = scanner.nextInt();
                deleteEpicById(idTask1);
                break;
            case 8:
                System.out.println("Введите id эпика :");
                int idEpic = scanner.nextInt();
                ;
                for (Subtask sub : getAllSubtaskFromEpic(idEpic)) {
                    System.out.println("1. " + sub);
                }
                break;
        }
    }

    public void subtaskApp(int command) {
        switch (command) {
            case 0:
                return;
            case 1:
                System.out.println("Введите id эпика, для которого добавляем подзадачу :");
                int idDesiredTask = scanner.nextInt();
                System.out.println("Введите название подзадачи :");
                String newTitleSubtask = scanner.nextLine();
                scanner.nextLine();

                System.out.println("Введите описание подзадачи :");
                String newDescriptionSubtask = scanner.nextLine();
                Subtask subtask1 = new Subtask(newTitleSubtask, newDescriptionSubtask, idDesiredTask, StatusOfTask.IN_PROGRESS);
                createNewSubtask(subtask1);
                subtaskHashMap.put(subtask1.getId(), subtask1);
                epicHashMap.get(idDesiredTask).getListSubtask().add(subtask1.getId());
                break;
            case 2:
                int counter = 1;
                for (Subtask subtask : getAllSubtasks()) {
                    System.out.println(counter + ". " + subtask);
                    counter++;
                }
                break;
            case 3:
                deleteAllSubtasks();
                System.out.println("Список подзадач пуст!");
                break;
            case 4:
                System.out.println("Введите id подзадачи, которую хотите обновить:");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Введите описание подзадачи :");
                String newDescriptionSub = scanner.nextLine();

                Subtask subtask = new Subtask(subtaskHashMap.get(id).getTitle(), newDescriptionSub, subtaskHashMap.get(id).getEpicId(), StatusOfTask.IN_PROGRESS);
                updateSubtask(subtask);
                break;
            case 6:
                System.out.println("Введите id эпика, который хотите найти:");
                int idTask = scanner.nextInt();
                System.out.println(getEpicById(idTask));
                break;
            case 7:
                System.out.println("Введите id подзадачи, который хотите удалить:");
                int idTask1 = scanner.nextInt();
                epicHashMap.get(subtaskHashMap.get(idTask1).getEpicId()).getListSubtask().remove(idTask1);
                deleteSubtaskById(idTask1);
                int idEpic = subtaskHashMap.get(idTask1).getEpicId();
                if (epicHashMap.get(idEpic).getListSubtask().isEmpty()) {
                    updateEpic(new Epic(epicHashMap.get(idEpic).getTitle(), epicHashMap.get(idEpic).getDescription(), epicHashMap.get(idEpic).getListSubtask(), StatusOfTask.DONE));
                }
                break;
        }
    }
}

