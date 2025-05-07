import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private ArrayList<Integer> listSubtask;

    public Epic(String title, String description, ArrayList<Integer> subtaskId, StatusOfTask status) {
        super(title, description, status);
        listSubtask = subtaskId;
    }

    public ArrayList<Integer> getListSubtask() {
        return listSubtask;
    }

    @Override
    public String toString() {
        return "Эпик { " +
                "название = " + super.getTitle() +
                ", " + "описание = " + super.getDescription() +
                ", " + "id = " + super.getId() + ", " + '\n' +
                "количество подзадач = " + listSubtask.size() +
                ", статус эпика = " + super.getStatusOfTask() +
                '}';
    }
}
