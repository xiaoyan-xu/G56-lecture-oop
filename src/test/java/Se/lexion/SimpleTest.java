package Se.lexion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleTest {

    @Test
    public void testBasicAssertion() {
        assertEquals(2, 1 + 1);
        assertTrue(true);
        assertNotNull("Hello");
    }

    @Test
    public void testAppRoleEnum() {
        AppRole userRole = AppRole.ROLE_APP_USER;
        AppRole adminRole = AppRole.ROLE_APP_ADMIN;

        assertNotNull(userRole);
        assertNotNull(adminRole);
        assertEquals("ROLE_APP_USER", userRole.name());
        assertEquals("ROLE_APP_ADMIN", adminRole.name());
    }

    @Test
    public void testPersonBasic() {
        Person person = new Person(1, "Test", "User", "test@example.com");
        assertEquals(1, person.getId());
        assertEquals("Test", person.getFirstName());
        assertEquals("User", person.getLastName());
        assertEquals("test@example.com", person.getEmail());
    }
}