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
                    4. ЗАВЕРШИТЬ РАБОТУ\s
                    """);
            int entity = scanner.nextInt();
            scanner.nextLine();

            switch (entity) {
                case 1:
                    while (true) {
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
                        taskManager.taskApp(commandTask);
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
                        taskManager.epicApp(commandEpic);
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
                        taskManager.subtaskApp(commandSubtask);
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

