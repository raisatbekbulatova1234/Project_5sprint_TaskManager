import java.util.HashMap;

public class Epic extends Task {

    public HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();
    public StatusOfTask statusOfEpic;

    public Epic(String title, String description) {
        super(title, description);
    }

}
