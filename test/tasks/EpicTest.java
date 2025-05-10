package tasks;

import enums.StatusOfTask;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EpicTest {
    @Test
    public void EpicsWithEqualIdShouldBeEqual() {
        Epic epic1 = new Epic("Проект 5", "Написать тесты", new ArrayList<>(), StatusOfTask.NEW);
        Epic epic2 = new Epic("Проект 4", "Отправить на ревью", new ArrayList<>(), StatusOfTask.DONE);
        epic2.setId(epic1.getId());
        assertEquals(epic2, epic1,
                "Ошибка! Наследники класса Task должны быть равны друг другу, если равен их id;");
    }
}
