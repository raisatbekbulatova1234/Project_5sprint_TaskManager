package tasks;

import enums.StatusOfTask;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubtaskTest {
    @Test
    public void SubtasksWithEqualIdShouldBeEqual() {
        Subtask subtask1 = new Subtask("Проект 5", "Написать тесты", 2, StatusOfTask.NEW);
        Subtask subtask2 = new Subtask("Проект 4", "Отправить на ревью", 4, StatusOfTask.DONE);
        subtask2.setId(subtask1.getId());
        assertEquals(subtask2, subtask1,
                "Ошибка! Наследники класса Task должны быть равны друг другу, если равен их id;");
    }
}
