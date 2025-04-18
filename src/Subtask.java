public class Subtask extends Task {

   public String epicName;
    public StatusOfTask statusOfSubtask;

    public Subtask(String title, String description, String epicName) {
        super(title, description);
        this.epicName = epicName;
    }
}
