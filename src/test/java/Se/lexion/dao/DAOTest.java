package Se.lexion.dao;

import Se.lexion.*;
import Se.lexion.sequencer.PersonIdSequencer;
import Se.lexion.sequencer.TodoItemIdSequencer;
import Se.lexion.sequencer.TodoItemTaskIdSequencer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class DAOTest {
    private AppUserDAOCollection appUserDAO;
    private PersonDAOCollection personDAO;
    private TodoItemDAOCollection todoItemDAO;
    private TodoItemTaskDAOCollection todoItemTaskDAO;

    @BeforeEach
    void setUp() {
        appUserDAO = new AppUserDAOCollection();
        personDAO = new PersonDAOCollection();
        todoItemDAO = new TodoItemDAOCollection();
        todoItemTaskDAO = new TodoItemTaskDAOCollection();

        // Reset sequencers
        PersonIdSequencer.setCurrentId(0);
        TodoItemIdSequencer.setCurrentId(0);
        TodoItemTaskIdSequencer.setCurrentId(0);
    }

    @Test
    void testSequencers() {
        assertEquals(1, PersonIdSequencer.nextId());
        assertEquals(2, PersonIdSequencer.nextId());
        assertEquals(2, PersonIdSequencer.getCurrentId());

        assertEquals(1, TodoItemIdSequencer.nextId());
        assertEquals(1, TodoItemIdSequencer.getCurrentId());

        assertEquals(1, TodoItemTaskIdSequencer.nextId());
        assertEquals(1, TodoItemTaskIdSequencer.getCurrentId());
    }

    @Test
    void testAppUserDAO() {
        AppUser user1 = new AppUser("john123", "password", AppRole.ROLE_APP_USER);
        AppUser user2 = new AppUser("admin", "secret", AppRole.ROLE_APP_ADMIN);

        // Test persist
        appUserDAO.persist(user1);
        appUserDAO.persist(user2);

        // Test findByUsername
        AppUser found = appUserDAO.findByUsername("john123");
        assertNotNull(found);
        assertEquals("john123", found.getUsername());
        assertEquals(AppRole.ROLE_APP_USER, found.getRole());

        // Test findAll
        Collection<AppUser> allUsers = appUserDAO.findAll();
        assertEquals(2, allUsers.size());

        // Test remove
        appUserDAO.remove("john123");
        assertEquals(1, appUserDAO.findAll().size());
        assertNull(appUserDAO.findByUsername("john123"));
    }

    @Test
    void testPersonDAO() {
        Person person1 = new Person(PersonIdSequencer.nextId(), "John", "Doe", "john@example.com");
        Person person2 = new Person(PersonIdSequencer.nextId(), "Jane", "Smith", "jane@example.com");

        // Test persist
        personDAO.persist(person1);
        personDAO.persist(person2);

        // Test findById
        Person found = personDAO.findById(1);
        assertNotNull(found);
        assertEquals("John", found.getFirstName());

        // Test findByEmail
        Person foundByEmail = personDAO.findByEmail("jane@example.com");
        assertNotNull(foundByEmail);
        assertEquals("Jane", foundByEmail.getFirstName());

        // Test findAll
        Collection<Person> allPersons = personDAO.findAll();
        assertEquals(2, allPersons.size());

        // Test remove
        personDAO.remove(1);
        assertEquals(1, personDAO.findAll().size());
        assertNull(personDAO.findById(1));
    }

    @Test
    void testTodoItemDAO() {
        Person creator = new Person(PersonIdSequencer.nextId(), "John", "Doe", "john@example.com");
        LocalDate deadline = LocalDate.now().plusDays(7);
        TodoItem item1 = new TodoItem(TodoItemIdSequencer.nextId(), "Buy milk", "Get milk from store", deadline, creator);
        TodoItem item2 = new TodoItem(TodoItemIdSequencer.nextId(), "Fix car", "Repair brakes", LocalDate.now().minusDays(1), creator);

        // Test persist
        todoItemDAO.persist(item1);
        todoItemDAO.persist(item2);

        // Test findById
        TodoItem found = todoItemDAO.findById(1);
        assertNotNull(found);
        assertEquals("Buy milk", found.getTitle());

        // Test findByTitleContains
        Collection<TodoItem> milkItems = todoItemDAO.findByTitleContains("milk");
        assertEquals(1, milkItems.size());

        // Test findByPersonId
        Collection<TodoItem> johnItems = todoItemDAO.findByPersonId(creator.getId());
        assertEquals(2, johnItems.size());

        // Test findByDeadlineBefore
        Collection<TodoItem> overdue = todoItemDAO.findByDeadlineBefore(LocalDate.now());
        assertEquals(1, overdue.size());
        assertEquals("Fix car", overdue.iterator().next().getTitle());

        // Test findAllByDoneStatus
        Collection<TodoItem> notDone = todoItemDAO.findAllByDoneStatus(false);
        assertEquals(2, notDone.size());

        // Test remove
        todoItemDAO.remove(1);
        assertEquals(1, todoItemDAO.findAll().size());
    }

    @Test
    void testTodoItemTaskDAO() {
        Person creator = new Person(PersonIdSequencer.nextId(), "John", "Doe", "john@example.com");
        Person assignee = new Person(PersonIdSequencer.nextId(), "Jane", "Smith", "jane@example.com");
        TodoItem todoItem = new TodoItem(TodoItemIdSequencer.nextId(), "Task", "Description", LocalDate.now().plusDays(1), creator);

        TodoItemTask task1 = new TodoItemTask(TodoItemTaskIdSequencer.nextId(), todoItem);
        TodoItemTask task2 = new TodoItemTask(TodoItemTaskIdSequencer.nextId(), todoItem);
        task2.setAssignee(assignee);

        // Test persist
        todoItemTaskDAO.persist(task1);
        todoItemTaskDAO.persist(task2);

        // Test findById
        TodoItemTask found = todoItemTaskDAO.findById(1);
        assertNotNull(found);
        assertFalse(found.isAssigned());

        // Test findByAssignedStatus
        Collection<TodoItemTask> assigned = todoItemTaskDAO.findByAssignedStatus(true);
        assertEquals(1, assigned.size());

        Collection<TodoItemTask> unassigned = todoItemTaskDAO.findByAssignedStatus(false);
        assertEquals(1, unassigned.size());

        // Test findByPersonId
        Collection<TodoItemTask> janeTasks = todoItemTaskDAO.findByPersonId(assignee.getId());
        assertEquals(1, janeTasks.size());

        // Test remove
        todoItemTaskDAO.remove(1);
        assertEquals(1, todoItemTaskDAO.findAll().size());
    }
}
