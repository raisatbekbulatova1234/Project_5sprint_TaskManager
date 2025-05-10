package tasks;

import enums.StatusOfTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class TaskTest {

    @Test
    void tasksWithEqualIdShouldBeEqual() {
        Task task = new Task("Учеба", "Сдать 5-й проект", StatusOfTask.NEW);
        Task task2 = new Task("Учеба", "Сдать 5-й проект", StatusOfTask.IN_PROGRESS);
        task2.setId(task.getId());
        assertEquals(task2, task,
                "Ошибка! Экземпляры класса Task должны быть равны друг другу, если равен их id");
    }
}