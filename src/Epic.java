import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private List<Integer> listSubtask = new ArrayList<>();
    //private int epicId;

    public Epic(String title, String description, int subtaskId, StatusOfTask status) {
        super(title, description, status);
        listSubtask.add(subtaskId);
    }

    public List<Integer> getListSubtask() {
        return listSubtask;
    }

    public void setListSubtask(List<Integer> listSubtask) {
        this.listSubtask = listSubtask;
    }

    @Override
    public String toString() {
        return "Эпик { " +
                "название = " + super.getTitle() +
                ", " + "описание = " + super.getDescription() +
                ", " + "id = " + super.getId() + ", " + '\n' +
                "подзадачи = " + listSubtask +
                // " статус эпика = " + statusOfEpic +
                '}';
    }
}
