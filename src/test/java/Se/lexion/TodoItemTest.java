package Se.lexion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class TodoItemTest {

    @Test
    void testTodoItemCreation() {
        Person creator = new Person(1, "John", "Doe", "john@example.com");
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item = new TodoItem(1, "Change tires", "Replace winter tires", deadline, creator);

        assertEquals(1, item.getId());
        assertEquals("Change tires", item.getTitle());
        assertEquals("Replace winter tires", item.getTaskDescription());
        assertEquals(deadline, item.getDeadLine());
        assertEquals(creator, item.getCreator());
        assertFalse(item.isDone());
    }

    @Test
    void testNullTitle() {
        Person creator = new Person(1, "John", "Doe", "john@example.com");
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        assertThrows(IllegalArgumentException.class, () -> {
            new TodoItem(1, null, "Description", deadline, creator);
        });
    }

    @Test
    void testEmptyTitle() {
        Person creator = new Person(1, "John", "Doe", "john@example.com");
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        assertThrows(IllegalArgumentException.class, () -> {
            new TodoItem(1, "", "Description", deadline, creator);
        });
    }

    @Test
    void testNullDeadline() {
        Person creator = new Person(1, "John", "Doe", "john@example.com");
        assertThrows(IllegalArgumentException.class, () -> {
            new TodoItem(1, "Title", "Description", null, creator);
        });
    }

    @Test
    void testIsOverdue() {
        Person creator = new Person(1, "John", "Doe", "john@example.com");

        // Past date - should be overdue
        LocalDate pastDate = LocalDate.now().minusDays(1);
        TodoItem pastItem = new TodoItem(1, "Past Task", "Description", pastDate, creator);
        assertTrue(pastItem.isOverdue());

        // Future date - should not be overdue
        LocalDate futureDate = LocalDate.now().plusDays(1);
        TodoItem futureItem = new TodoItem(2, "Future Task", "Description", futureDate, creator);
        assertFalse(futureItem.isOverdue());
    }

    @Test
    void testToString() {
        Person creator = new Person(1, "John", "Doe", "john@example.com");
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item = new TodoItem(1, "Change tires", "Replace winter tires", deadline, creator);

        String result = item.toString();
        System.out.println("Actual toString result: " + result);

        // Basic check that toString returns a non-empty string with key information
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.contains("1")); // Contains ID
        assertTrue(result.contains("Change tires")); // Contains title
        assertTrue(result.contains("2025-12-31")); // Contains deadline
    }

    @Test
    void testEqualsAndHashCode() {
        Person creator1 = new Person(1, "John", "Doe", "john@example.com");
        Person creator2 = new Person(2, "Jane", "Smith", "jane@example.com");
        LocalDate deadline = LocalDate.of(2025, 12, 31);

        TodoItem item1 = new TodoItem(1, "Change tires", "Replace winter tires", deadline, creator1);
        TodoItem item2 = new TodoItem(1, "Change tires", "Replace winter tires", deadline, creator2);

        // Should be equal even with different creators (Person objects excluded)
        assertEquals(item1, item2);
        assertEquals(item1.hashCode(), item2.hashCode());
    }
}

   /* @Test
    void testGetSummary() {
        Person creator = new Person(1, "John", "Doe", "john@example.com");
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item = new TodoItem(1, "Change tires", "Replace winter tires", deadline, creator);

        String summary = item.getSummary();
        assertTrue(summary.contains("id: 1"));
        assertTrue(summary.contains("title: Change tires"));
        assertTrue(summary.contains("taskDescription: Replace winter tires"));
    }*/


