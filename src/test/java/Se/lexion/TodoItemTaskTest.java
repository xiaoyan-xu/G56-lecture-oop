package Se.lexion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import Se.lexion.Person;
import Se.lexion.TodoItem;
import Se.lexion.TodoItemTask;

public class TodoItemTaskTest {

    @Test
    void testTodoItemTaskCreation() {
        Person creator = new Person(1, "John", "Doe", "john@example.com");
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem todoItem = new TodoItem(1, "Change tires", "Replace winter tires", deadline, creator);
        TodoItemTask task = new TodoItemTask(1, todoItem);

        assertEquals(1, task.getId());
        assertEquals(todoItem, task.getTodoItem());
        assertNull(task.getAssignee());
        assertFalse(task.isAssigned());
    }

    @Test
    void testNullTodoItem() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TodoItemTask(1, null);
        });
    }

    @Test
    void testAssignedEncapsulation() {
        Person creator = new Person(1, "John", "Doe", "john@example.com");
        Person assignee = new Person(2, "Jane", "Smith", "jane@example.com");
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem todoItem = new TodoItem(1, "Change tires", "Replace winter tires", deadline, creator);
        TodoItemTask task = new TodoItemTask(1, todoItem);

        // Initially not assigned
        assertFalse(task.isAssigned());

        // Assign someone
        task.setAssignee(assignee);
        assertTrue(task.isAssigned());
        assertEquals(assignee, task.getAssignee());

        // Unassign
        task.setAssignee(null);
        assertFalse(task.isAssigned());
        assertNull(task.getAssignee());
    }

    /*@Test
    void testGetSummary() {
        Person creator = new Person(1, "John", "Doe", "john@example.com");
        Person assignee = new Person(2, "Jane", "Smith", "jane@example.com");
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem todoItem = new TodoItem(1, "Change tires", "Replace winter tires", deadline, creator);
        TodoItemTask task = new TodoItemTask(1, todoItem);

        // Test unassigned

        assertTrue(summary1.contains("id: 1"));
        assertTrue(summary1.contains("assigned: false"));
        assertTrue(summary1.contains("assignee: null"));

        // Test assigned
        task.setAssignee(assignee);
        String summary2 = task.getSummary();
        assertTrue(summary2.contains("assigned: true"));
        assertTrue(summary2.contains("Jane Smith"));
    }
    */

    @Test
    void testToString() {
        Person creator = new Person(1, "John", "Doe", "john@example.com");
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem todoItem = new TodoItem(1, "Change tires", "Replace winter tires", deadline, creator);
        TodoItemTask task = new TodoItemTask(1, todoItem);

        String result = task.toString();
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("assigned=false"));
        assertTrue(result.contains("Change tires"));
    }

    @Test
    void testEqualsAndHashCode() {
        Person creator = new Person(1, "John", "Doe", "john@example.com");
        Person assignee1 = new Person(2, "Jane", "Smith", "jane@example.com");
        Person assignee2 = new Person(3, "Bob", "Wilson", "bob@example.com");
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem todoItem = new TodoItem(1, "Change tires", "Replace winter tires", deadline, creator);

        TodoItemTask task1 = new TodoItemTask(1, todoItem);
        TodoItemTask task2 = new TodoItemTask(1, todoItem);

        task1.setAssignee(assignee1);
        task2.setAssignee(assignee2);

        // Should be equal even with different assignees (Person objects excluded)
        assertEquals(task1, task2);
        assertEquals(task1.hashCode(), task2.hashCode());
    }
}
