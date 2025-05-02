import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static TaskManager taskManager = new TaskManager();
    static Scanner scanner = new Scanner(System.in);

    private static void taskApp(int commandTask) {
        switch (commandTask) {
            case 0:
                return;
            case 1:
                System.out.println("Введите название задачи :");

                String titleTask = scanner.nextLine();

                System.out.println("Введите описание задачи :");
                String descriptionTask = scanner.nextLine();

                taskManager.createNewTask(new Task(titleTask, descriptionTask, StatusOfTask.NEW));
                break;
            case 2:
                int counter = 1;
                for (Task task : taskManager.getAllTasks()) {
                    System.out.println(counter + ". " + task);
                    counter++;
                }
                break;
            case 3:
                taskManager.deleteAllTasks();
                System.out.println("Список задач пуст!");
                break;
            case 4:
                System.out.println("Введите id задачи, которую хотите обновить:");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Введите описание задачи :");
                String newDescriptionTask = scanner.nextLine();

                Task task = taskManager.getTaskById(id);
                taskManager.updateTask(new Task(task.getTitle(), newDescriptionTask, StatusOfTask.IN_PROGRESS));
                break;
            case 5:
                System.out.println("Введите id задачи, которую хотите найти:");
                int idTask = scanner.nextInt();
                System.out.println(taskManager.getTaskById(idTask));
                break;
            case 6:
                System.out.println("Введите id задачи, которую хотите удалить:");
                int idTask1 = scanner.nextInt();
                taskManager.deleteTaskById(idTask1);
                break;
        }
    }

    private static void epicApp(int commandEpic) {
        switch (commandEpic) {
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
                int epicId = taskManager.counterOfTasks++;
                Subtask subtask = new Subtask(titleSubtask, descriptionSubtask, epicId, StatusOfTask.NEW);
                subtask.setId(taskManager.counterOfTasks++);

                List<Integer> integerListSubtask = new ArrayList<>();
                integerListSubtask.add(subtask.getId());
                taskManager.createNewEpic(new Epic(epicTitle, descriptionOfEpic, integerListSubtask, StatusOfTask.NEW));
                break;
            case 2:
                int counter = 1;
                for (Epic epic : taskManager.getAllEpics()) {
                    System.out.println(counter + ". " + epic);
                    counter++;
                }
                break;
            case 3:
                taskManager.deleteAllEpics();
                System.out.println("Список эпиков пуст!");
                break;
            case 4:
                System.out.println("Введите id эпика, который хотите обновить:");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Введите описание эпика :");
                String newDescriptionTask = scanner.nextLine();
                List<Integer> integerList = taskManager.epicHashMap.get(id).getListSubtask();

                Epic epic = new Epic(taskManager.epicHashMap.get(id).getTitle(), newDescriptionTask, integerList, StatusOfTask.IN_PROGRESS);
                taskManager.updateEpic(epic);
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
                taskManager.subtaskHashMap.put(subtask1.getId(), subtask1);
                taskManager.epicHashMap.get(idDesiredTask).getListSubtask().add(subtask1.getId());
                break;
            case 6:
                System.out.println("Введите id эпика, который хотите найти:");
                int idTask = scanner.nextInt();
                System.out.println(taskManager.getTaskById(idTask));
                break;
            case 7:
                System.out.println("Введите id эпика, который хотите удалить:");
                int idTask1 = scanner.nextInt();
                taskManager.deleteEpicById(idTask1);
                break;
            case 8:
                System.out.println("Введите id эпика :");
                int idEpic = scanner.nextInt();
                ;
                for (Subtask sub : taskManager.getAllSubtaskFromEpic(idEpic)) {
                    System.out.println("1. " + sub);
                }
                break;
        }
    }

    private static void subtaskApp(int commandSubtask) {
        switch (commandSubtask) {
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
                taskManager.createNewSubtask(subtask1);
                taskManager.subtaskHashMap.put(subtask1.getId(), subtask1);
                taskManager.epicHashMap.get(idDesiredTask).getListSubtask().add(subtask1.getId());
                break;
            case 2:
                int counter = 1;
                for (Subtask subtask : taskManager.getAllSubtasks()) {
                    System.out.println(counter + ". " + subtask);
                    counter++;
                }
                break;
            case 3:
                taskManager.deleteAllSubtasks();
                System.out.println("Список подзадач пуст!");
                break;
            case 4:
                System.out.println("Введите id подзадачи, которую хотите обновить:");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Введите описание подзадачи :");
                String newDescriptionSub = scanner.nextLine();

                Subtask subtask = new Subtask(taskManager.subtaskHashMap.get(id).getTitle(), newDescriptionSub, taskManager.subtaskHashMap.get(id).getEpicId(), StatusOfTask.IN_PROGRESS);
                taskManager.updateSubtask(subtask);
                break;
            case 6:
                System.out.println("Введите id эпика, который хотите найти:");
                int idTask = scanner.nextInt();
                System.out.println(taskManager.getEpicById(idTask));
                break;
            case 7:
                System.out.println("Введите id подзадачи, который хотите удалить:");
                int idTask1 = scanner.nextInt();
                taskManager.epicHashMap.get(taskManager.subtaskHashMap.get(idTask1).getEpicId()).getListSubtask().remove(idTask1);
                taskManager.deleteSubtaskById(idTask1);
                int idEpic = taskManager.subtaskHashMap.get(idTask1).getEpicId();
                if (taskManager.epicHashMap.get(idEpic).getListSubtask().isEmpty()) {
                    taskManager.updateEpic(new Epic(taskManager.epicHashMap.get(idEpic).getTitle(), taskManager.epicHashMap.get(idEpic).getDescription(), taskManager.epicHashMap.get(idEpic).getListSubtask(), StatusOfTask.DONE));
                }
                break;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    С чем вы хотите работать?\s
                    1. ЗАДАЧИ\s
                    2. ЭПИКИ\s
                    3. ПОДЗАДАЧИ\s
                    4. ЗАВЕРШИТЬ РАБОТУ\s
                    """);
            int entity = scanner.nextInt();
            scanner.nextLine();
            boolean t = true;
            switch (entity) {
                case 1:
                    while (t) {
                        System.out.println("Интерфейс приложения: \n" +
                                "1. Создать новую задачу \n" +
                                "2. Вывести список всех задач на экран \n" +
                                "3. Очистить список задач \n" +
                                "4. Обновить задачу \n" +
                                "5. Получить задачу по идентификатору \n" +
                                "6. Удалить задачу по идентификатору \n" +
                                "0. Выйти в основное меню \n");

                        int commandTask = scanner.nextInt();
                        scanner.nextLine();
                        taskApp(commandTask);
                        break;
                    }
                    break;

                case 2:
                    while (true) {
                        System.out.println("Интерфейс приложения: \n" +
                                "1. Создать новый эпик \n" +
                                "2. Вывести список всех эпиков на экран \n" +
                                "3. Очистить список эпиков \n" +
                                "4. Обновить эпик \n" +
                                "5. Добавить подзадачу \n" +
                                "6. Получить эпик по идентификатору \n" +
                                "7. Удалить эпик по идентификатору \n" +
                                "8. Получить список всех подзадач \n" +
                                "0. Завершить работу \n");
                        int commandEpic = scanner.nextInt();
                        scanner.nextLine();
                        epicApp(commandEpic);
                        break;
                    }
                    break;
                case 3:
                    while (true) {
                        System.out.println("Интерфейс приложения: \n" +
                                "1. Создать новую подзадачу \n" +
                                "2. Вывести список всех подзадач на экран \n" +
                                "3. Очистить список подзадач \n" +
                                "4. Обновить подзадачу \n" +
                                "5. Получить подзадачу по идентификатору \n" +
                                "6. Удалить подзадачу по идентификатору \n" +
                                "0. Завершить работу \n");
                        int commandSubtask = scanner.nextInt();
                        scanner.nextLine();
                        subtaskApp(commandSubtask);
                        break;
                    }
                    break;
                case 4:
                    System.out.println("Работа приложения завершена. До свидания!");
                    return;
            }
        }
    }
}

