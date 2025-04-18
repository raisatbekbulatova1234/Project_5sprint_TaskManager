import java.util.HashMap;

public class Epic extends Task {

    public HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();
    public StatusOfTask statusOfEpic;


    public Epic(String title, String description, Subtask subtask) {
        super(title, description);
        subtaskHashMap.put(subtask.getId(), subtask);
        statusOfEpic = StatusOfTask.NEW;
    }

    @Override
    public String toString() {
        return "Эпик { " +
                "название = " + super.getTitle() +
                ", " + "описание = " + super.getDescription() +
                ", " + "id = " + super.getId() + ", " + '\n' +
                "подзадачи = " + subtaskHashMap +
                " статус эпика = " + statusOfEpic +
                '}';
    }
}
