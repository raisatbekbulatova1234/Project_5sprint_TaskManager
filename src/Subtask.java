public class Subtask extends Task {

    private int epicId;

    public Subtask(String title, String description, int epicId, StatusOfTask status) {
        super(title, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "Подзадача { " +
                " id = " + super.getId() +
                " название = " + super.getTitle() +
                ", описание = " + super.getDescription() +
                ", статус подзадачи = " + super.getStatusOfTask() +
                '}';
    }
}
