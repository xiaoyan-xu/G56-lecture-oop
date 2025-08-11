package Se.lexion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Se.lexion.Person;

public class PersonTest {

    @Test
    void testPersonCreation() {
        Person person = new Person(1, "John", "Doe", "john@example.com");
        assertEquals(1, person.getId());
        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals("john@example.com", person.getEmail());
    }

    @Test
    void testNullFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Person(1, null, "Doe", "john@example.com");
        });
    }

    @Test
    void testNullLastName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Person(1, "John", null, "john@example.com");
        });
    }

    @Test
    void testNullEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Person(1, "John", "Doe", null);
        });
    }

    /*@Test
    void testGetSummary() {
        Person person = new Person(4, "Nisse", "Olsson", "nisse@gmail.com");
        String expected = "id: 4, name: Nisse Olsson, email: nisse@gmail.com";
        assertEquals(expected, person.getSummary());
    }*/
    @Test
    void testCredentials() {
        Person person = new Person(1, "John", "Doe", "john@example.com");
        AppUser credentials = new AppUser("john123", "password", AppRole.ROLE_APP_USER);
        person.setCredentials(credentials);
        assertEquals(credentials, person.getCredentials());
    }

    @Test
    void testToString() {
        Person person = new Person(4, "Nisse", "Olsson", "nisse@gmail.com");
        String expected = "{id: 4, name: Nisse Olsson, email: nisse@gmail.com}";
        assertEquals(expected, person.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Person person1 = new Person(1, "John", "Doe", "john@example.com");
        Person person2 = new Person(1, "John", "Doe", "john@example.com");

        // Add different credentials to test exclusion
        person1.setCredentials(new AppUser("john1", "pass1", AppRole.ROLE_APP_USER));
        person2.setCredentials(new AppUser("john2", "pass2", AppRole.ROLE_APP_ADMIN));

        assertEquals(person1, person2);
        assertEquals(person1.hashCode(), person2.hashCode());
    }


}