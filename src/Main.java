import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    С чем вы хотите работать?\s
                    1. ЗАДАЧИ\s
                    2. ЭПИКИ\s
                    3. ПОДЗАДАЧИ\s
                    """);
            int entity = scanner.nextInt();
            scanner.nextLine();

            switch (entity) {
                case 1:
                    taskManager.taskMenu();
                    break;
                case 2:
                    taskManager.epicMenu();
                    break;
                case 3:
                    taskManager.subtaskMenu();
                    break;
            }

            int command = scanner.nextInt();
            scanner.nextLine();
            switch (command) {
                case 0:
                    System.out.println("Работа приложения завершена. Пока!");
                    return;
                case 1:
                    System.out.println("Введите название задачи :");
                    String titleTask = scanner.nextLine();

                    System.out.println("Введите описание задачи :");
                    String descriptionTask = scanner.nextLine();

                    taskManager.createNewTask(new Task(titleTask, descriptionTask, StatusOfTask.NEW));
                    break;
                case 2:
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
                    taskManager.createNewTask(new Epic(epicTitle, descriptionOfEpic, subtask.getId(), StatusOfTask.NEW));
                    break;
                case 3:
                    int counter = 1;
                    for (Task task : taskManager.getAllTasks()) {
                        System.out.println(counter + ". " + task);
                        counter++;
                    }
                    break;
                case 4:
                    taskManager.deleteAllTasks();
                    System.out.println("Список задач пуст!");
                    break;
                case 5:
                    System.out.println("Введите id задачи, которую хотите обновить:");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Введите описание задачи :");
                    String newDescriptionTask = scanner.nextLine();

                    Task task = taskManager.getTaskById(id);
                    taskManager.updateTask(new Task(task.getTitle(), newDescriptionTask, StatusOfTask.IN_PROGRESS));
                    break;
                case 6:
                    System.out.println("Введите id эпика, для которого добавляем подзадачу :");
                    int idDesiredTask = scanner.nextInt();
                    System.out.println("Введите название подзадачи :");
                    String newTitleSubtask = scanner.nextLine();
                    scanner.nextLine();

                    System.out.println("Введите описание подзадачи :");
                    String newDescriptionSubtask = scanner.nextLine();
                    Subtask subtask1 = new Subtask(newTitleSubtask, newDescriptionSubtask, idDesiredTask, StatusOfTask.IN_PROGRESS);

                    break;
                case 7:
                    System.out.println("Введите id задачи, которую хотите найти:");
                    int idTask = scanner.nextInt();
                    System.out.println(taskManager.getTaskById(idTask));
                    break;
                case 8:
                    System.out.println("Введите id задачи, которую хотите удалить:");
                    int idTask1 = scanner.nextInt();
                    taskManager.deleteTaskById(idTask1);
                    break;

            }
        }

    }
}
