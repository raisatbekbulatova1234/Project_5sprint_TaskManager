import java.util.HashMap;

public class Epic extends Task {

    public Subtask subtaskHashMap;
    public StatusOfTask statusOfEpic;


    public Epic(String title, String description, Subtask subtaskHashMap) {
        super(title, description);
        this.subtaskHashMap = subtaskHashMap;
        statusOfEpic = StatusOfTask.NEW;
    }
}
