import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Интерфейс приложения: \n" +
                    "1. Создать новую задачу \n" +
                    "2. Вывести список всех задач на экран \n" +
                    "3. Очистить список задач \n" +
                    "4. Обновить задачу \n" +
                    "5. Добавить подзадачу \n" +
                    "6. Получить задачу по идентификатору \n" +
                    "7. Удалить задачу по идентификатору \n" +
                    "0. Завершить работу \n");
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

                    taskManager.createNewTask(new Task(titleTask, descriptionTask));
                    break;
                case 2:
                    for (Task task : taskManager.getAllTasks()) {
                        System.out.println(task);
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
                    taskManager.updateTask(new Task(task.getTitle(), newDescriptionTask));
                    break;
                case 5:
                    System.out.println("Введите название задачи, для которой добавляем подзадачу :");
                    int idDesiredTask = scanner.nextInt();
                    System.out.println("Введите название подзадачи :");
                    String titleSubtask = scanner.nextLine();

                    System.out.println("Введите описание подзадачи :");
                    String descriptionSubtask = scanner.nextLine();

                    Task task1 = taskManager.getTaskById(idDesiredTask);
                    String epicTitle = task1.getTitle();
                    Subtask subtask = new Subtask(titleSubtask, task1.getDescription(), epicTitle);
                    taskManager.createNewTask(new Epic(epicTitle, task1.getDescription(), subtask));
                    break;
                case 6:
                    System.out.println("Введите id задачи, которую хотите найти:");
                    int idTask = scanner.nextInt();
                    System.out.println(taskManager.getTaskById(idTask));
                    break;
                case 7:
                    System.out.println("Введите id задачи, которую хотите удалить:");
                    int idTask1 = scanner.nextInt();
                    taskManager.deleteById(idTask1);
                    break;

            }
        }

    }
}
