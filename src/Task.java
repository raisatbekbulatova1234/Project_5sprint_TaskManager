import java.util.Objects;

public class Task {

    private String title;
    private String description;
    private int id;
    private StatusOfTask statusOfTask;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.id = TaskManager.counterOfTasks++;
        statusOfTask = StatusOfTask.NEW;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusOfTask getStatusOfTask() {
        return statusOfTask;
    }

    public void setStatusOfTask(StatusOfTask statusOfTask) {
        this.statusOfTask = statusOfTask;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id);
    }

    @Override
    public String toString() {
        return "Task {" +
                "title = '" + title + '\'' +
                ", description = '" + description + '\'' +
                ", id = " + id +
                ", statusOfTask = " + statusOfTask +
                '}';
    }
}
