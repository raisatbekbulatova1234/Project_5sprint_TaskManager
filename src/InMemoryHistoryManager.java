import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    HistoryManager historyManager;

    public InMemoryHistoryManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    public InMemoryHistoryManager() {
        this.historyManager = Managers.getDefaultHistory();
    }


    private static final ArrayList<Task> viewedTasks = new ArrayList<>();

    public void addTask(Task task) {
        if (viewedTasks.size() == 10) {
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
