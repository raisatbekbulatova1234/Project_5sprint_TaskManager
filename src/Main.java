import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Интерфейс приложения: \n" +
                    "1. Создать новую задачу \n" +
                    "2. Создать новый эпик \n" +
                    "3. Вывести список всех задач на экран \n" +
                    "4. Очистить список задач \n" +
                    "5. Обновить задачу \n" +
                    "6. Добавить подзадачу \n" +
                    "7. Получить задачу по идентификатору \n" +
                    "8. Удалить задачу по идентификатору \n" +
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
                    System.out.println("Введите название эпика :");
                    String epicTitle = scanner.nextLine();
                    System.out.println("Введите описание эпика :");
                    String descriptionOfEpic = scanner.nextLine();

                    System.out.println("Введите название подзадачи :");
                    String titleSubtask = scanner.nextLine();

                    System.out.println("Введите описание подзадачи :");
                    String descriptionSubtask = scanner.nextLine();

                    Subtask subtask = new Subtask(titleSubtask, descriptionSubtask, epicTitle);
                    //taskManager.subtaskHashMap.put(subtask.getId(), subtask);
                    taskManager.createNewTask(new Epic(epicTitle, descriptionOfEpic, subtask));
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
                    taskManager.updateTask(new Task(task.getTitle(), newDescriptionTask));
                    break;
                case 6:
                    System.out.println("Введите id эпика, для которого добавляем подзадачу :");
                    int idDesiredTask = scanner.nextInt();
                    System.out.println("Введите название подзадачи :");
                    String newTitleSubtask = scanner.nextLine();
                    scanner.nextLine();

                    System.out.println("Введите описание подзадачи :");
                    String newDescriptionSubtask = scanner.nextLine();
                    Subtask subtask1 = new Subtask(newTitleSubtask, newDescriptionSubtask, taskManager.epicHashMap.get(idDesiredTask).getTitle());

                    taskManager.epicHashMap.get(idDesiredTask).subtaskHashMap.put(subtask1.getId(), subtask1);

//                    Task task1 = taskManager.getTaskById(idDesiredTask);
//                    String epicTitle = task1.getTitle();
//
//                    Subtask subtask = new Subtask(titleSubtask, descriptionSubtask, epicTitle);
//                    taskManager.subtaskHashMap.put(subtask.getId(), subtask);
//
//
//                    Epic epic = new Epic(epicTitle, task1.getDescription(), subtask);
//                    taskManager.createNewTask(epic);
//                    taskManager.epicHashMap.put(epic.getId(), epic);
//
//                    taskManager.deleteById(idDesiredTask);
                    break;
                case 7:
                    System.out.println("Введите id задачи, которую хотите найти:");
                    int idTask = scanner.nextInt();
                    System.out.println(taskManager.getTaskById(idTask));
                    break;
                case 8:
                    System.out.println("Введите id задачи, которую хотите удалить:");
                    int idTask1 = scanner.nextInt();
                    taskManager.deleteById(idTask1);
                    break;

            }
        }

    }
}
