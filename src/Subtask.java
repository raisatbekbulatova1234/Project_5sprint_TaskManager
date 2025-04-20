public class Subtask extends Task {

   private int epicId;
   // public StatusOfTask statusOfSubtask;

    public Subtask(String title, String description, int epicId, StatusOfTask status) {
        super(title, description, status);
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Подзадача { " +
                "название = " + super.getTitle()  +
                ", описание = " + super.getDescription()  +
              //  ", статус подзадачи = " + statusOfSubtask +
                '}';
    }
}
