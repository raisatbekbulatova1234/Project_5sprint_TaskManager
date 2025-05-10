package managers;

import tasks.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int MAX_SIZE = 10;

    private static final ArrayList<Task> viewedTasks = new ArrayList<>();

    public void addTask(Task task) {
        if (viewedTasks.size() == MAX_SIZE) {
            viewedTasks.removeFirst();
            viewedTasks.add(task);
        } else
            viewedTasks.add(task);
    }

    @Override
    public ArrayList<Task> getHistory() {
        if (!viewedTasks.isEmpty()) {
            return viewedTasks;
        } else {
            System.out.println("Список просмотренных задач пуст!");
            return null;
        }
    }
}
