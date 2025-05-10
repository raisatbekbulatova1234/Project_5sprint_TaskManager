package managers;

import enums.StatusOfTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTaskManagerTest {
    private static TaskManager taskManager;
    static int testId = 0;

    @BeforeEach
    public void beforeEach() {
        taskManager = Managers.getDefault();
    }

    @Test
    void addNewTaskAndFindById() {
        final Task task = new Task("Учеба", "Сдать 5-й проект", StatusOfTask.NEW);
        taskManager.getTaskHashMap().put(task.getId(), task);
        final Task savedTask = taskManager.getTaskById(task.getId());
        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getAllTasks();
        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.getFirst(), "Задачи не совпадают.");
    }

    @Test
    void addNewEpicAndSubtasksAndFindById() {
        final Epic epic1 = new Epic("Программирование", "Пройти спринт 5", new ArrayList<>(),
                StatusOfTask.NEW);
        epic1.setId(testId);
        final Subtask subtask1 = new Subtask("Проект 4", "Отправить на ревью", epic1.getId(),
                StatusOfTask.DONE);
        subtask1.setId(testId++);
        final Subtask subtask2 = new Subtask("Проект 5", "Написать тесты", epic1.getId(),
                StatusOfTask.NEW);
        subtask2.setId(testId++);
        final Subtask subtask3 = new Subtask("Почитать про тестирование", "глубже изучить логику тестирования", epic1.getId(),
                StatusOfTask.IN_PROGRESS);
        subtask3.setId(testId++);

        taskManager.getEpicHashMap().put(epic1.getId(), epic1);
        taskManager.getSubtaskHashMap().put(subtask1.getId(), subtask1);
        taskManager.getSubtaskHashMap().put(subtask2.getId(), subtask2);
        taskManager.getSubtaskHashMap().put(subtask3.getId(), subtask3);

        final Task savedEpic = taskManager.getEpicById(epic1.getId());
        final Task savedSubtask1 = taskManager.getSubtaskById(subtask1.getId());
        final Task savedSubtask2 = taskManager.getSubtaskById(subtask2.getId());
        final Task savedSubtask3 = taskManager.getSubtaskById(subtask3.getId());

        assertNotNull(savedEpic, "Эпик не найден!");
        assertNotNull(savedSubtask2, "Подзадача не найдена!");
        assertEquals(epic1, savedEpic, "Эпики не равны!");
        assertEquals(subtask1, savedSubtask1, "Подзадачи не равны!");
        assertEquals(subtask3, savedSubtask3, "Подзадачи не равны!");

        final List<Epic> epics = taskManager.getAllEpics();
        assertNotNull(epics, "Эпики не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество эпиков.");
        assertEquals(epic1, epics.getFirst(), "Эпики не равны.");

        final List<Subtask> subtasks = taskManager.getAllSubtasks();
        assertNotNull(subtasks, "Подзадачи не возвращаются.");
        assertEquals(3, subtasks.size(), "Неверное количество подзадач.");
        assertEquals(savedSubtask1, subtasks.getFirst(), "Подзадачи не равны.");
    }

    @Test
    public void updateTaskShouldReturnTaskWithTheSameId() {
        final Task expected = new Task("Учеба", "Сдать 5-й проект", StatusOfTask.NEW);
        taskManager.getTaskHashMap().put(expected.getId(), expected);
        final Task newTask = new Task("Учеба.2", "Сдать 5-й проект.2", StatusOfTask.DONE);
        newTask.setId(expected.getId());
        taskManager.updateTask(newTask);
        final Task actual = taskManager.getTaskHashMap().get(newTask.getId());
        assertEquals(expected, actual, "Вернулась задача с другим id");
    }

    @Test
    public void updateEpicShouldReturnEpicWithTheSameId() {
        final Epic expected = new Epic("Программирование", "Пройти спринт 5", new ArrayList<>(),
                StatusOfTask.NEW);
        taskManager.getEpicHashMap().put(expected.getId(), expected);
        final Epic newEpic = new Epic("Программирование.2", "Пройти спринт 5.2", new ArrayList<>(),
                StatusOfTask.DONE);
        newEpic.setId(expected.getId());
        taskManager.updateEpic(newEpic);
        final Epic actual = taskManager.getEpicHashMap().get(newEpic.getId());
        assertEquals(expected, actual, "Вернулся эпик с другим id");
    }

    @Test
    public void updateSubtaskShouldReturnSubtaskWithTheSameId() {
        final Epic epic = new Epic("Программирование", "Пройти спринт 5", new ArrayList<>(),
                StatusOfTask.NEW);
        final Subtask expectedSubtask = new Subtask("Проект 5", "Написать тесты", epic.getId(),
                StatusOfTask.NEW);
        expectedSubtask.setId(epic.getId());
        taskManager.getSubtaskHashMap().put(expectedSubtask.getId(), expectedSubtask);
        final Subtask newSubtask = new Subtask("Проект 5.2", "Написать тесты.2", epic.getId(),
                StatusOfTask.NEW);
        newSubtask.setId(expectedSubtask.getId());
        final Subtask actual = taskManager.getSubtaskHashMap().get(newSubtask.getId());
        assertEquals(expectedSubtask, actual, "Вернулась подзадача с другим id");
    }

    @Test
    public void deleteTasksShouldReturnEmptyList() {
        Task task = new Task("Учеба", "Сдать 5-й проект", StatusOfTask.NEW);
        taskManager.getTaskHashMap().put(task.getId(), task);
        Task task2 = new Task("Учеба", "Сдать 4-й проект", StatusOfTask.NEW);
        taskManager.getTaskHashMap().put(task2.getId(), task2);
        taskManager.getTaskHashMap().clear();
        List<Task> tasks = taskManager.getAllTasks();
        assertTrue(tasks.isEmpty(), "Должен был вернуться пустой список!");
    }

    @Test
    public void deleteEpicsShouldReturnEmptyList() {
        Epic epic = new Epic("Программирование", "Пройти спринт 5", new ArrayList<>(),
                StatusOfTask.NEW);
        taskManager.getEpicHashMap().put(epic.getId(), epic);
        taskManager.getEpicHashMap().clear();
        List<Epic> epics = taskManager.getAllEpics();
        assertTrue(epics.isEmpty(), "Должен был вернуться пустой список!");
    }

    @Test
    public void deleteSubtasksShouldReturnEmptyList() {
        final Epic epic = new Epic("Программирование", "Пройти спринт 5", new ArrayList<>(),
                StatusOfTask.NEW);
        taskManager.getEpicHashMap().put(epic.getId(), epic);
        final Subtask subtask1 = new Subtask("Проект 4", "Отправить на ревью", epic.getId(),
                StatusOfTask.DONE);
        taskManager.getSubtaskHashMap().put(subtask1.getId(), subtask1);
        taskManager.getSubtaskHashMap().clear();
        List<Subtask> subtasks = taskManager.getAllSubtasks();
        assertTrue(subtasks.isEmpty(), "После удаления подзадач список должен быть пуст.");
    }

    @Test
    void TaskCreatedAndTaskAddedShouldHaveSameVariables() {
        //тест, в котором проверяется неизменность задачи (по всем полям) при добавлении задачи в менеджер

        Task expectedTask = new Task("Пойти в магазин", "Купить продукты", StatusOfTask.DONE);
        taskManager.getTaskHashMap().put(expectedTask.getId(), expectedTask);
        List<Task> list = taskManager.getAllTasks();
        Task actual = list.getFirst();
        assertEquals(expectedTask.getId(), actual.getId());
        assertEquals(expectedTask.getTitle(), actual.getTitle());
        assertEquals(expectedTask.getDescription(), actual.getDescription());
        assertEquals(expectedTask.getStatusOfTask(), actual.getStatusOfTask());
    }

    @Test
    void EpicCannotBeAddedToItselfAsSubtask() {
        //объект Epic нельзя добавить в самого себя в виде подзадачи
        final Epic epic = new Epic("Программирование", "Пройти спринт 5", new ArrayList<>(),
                StatusOfTask.NEW);
        final Subtask subtask1 = new Subtask("Проект 4", "Отправить на ревью", epic.getId(),
                StatusOfTask.DONE);
        subtask1.setId(epic.getId());
        assertEquals(-1, subtask1.getId());
    }

    @Test
    void SubtaskCannotBeAddedToItselfAsEpic() {
        //объект Subtask нельзя сделать своим же эпиком
        final Epic epic = new Epic("Программирование", "Пройти спринт 5", new ArrayList<>(),
                StatusOfTask.NEW);
        final Subtask subtask1 = new Subtask("Проект 4", "Отправить на ревью", epic.getId(),
                StatusOfTask.DONE);
        epic.setId(subtask1.getId());
        assertEquals(-1, subtask1.getId());
    }
}
