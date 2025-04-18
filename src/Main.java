import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        //HashMap<Integer, Task> taskHashMap = new HashMap<>();

        Task task1 = new Task("Оплатить интернет", "оплатить через личный кабинет 730 руб.");
        Task task2 = new Task("Оплатить аренду", "перевести хозяйке 9 тыщ");

//        taskHashMap.put(task1.getId(), task1);
//        taskHashMap.put(task2.getId(), task2);

        taskManager.createNewTask(task1);
        taskManager.createNewTask(task2);


        ArrayList<Task> taskArrayList =  taskManager.getAllTasks();
        System.out.println(taskArrayList);

        //taskManager.deleteAllTasks(taskManager.taskHashMap);

        taskManager.deleteById(task1.getId());


        //taskManager.updateTask(new Task("Позвонить маме", "обсудить переезд"));

        //System.out.println(taskManager.getTaskById(task2.getId()));

        ArrayList<Task> taskArrayList1 =  taskManager.getAllTasks();
        System.out.println(taskArrayList1);


    }
}
