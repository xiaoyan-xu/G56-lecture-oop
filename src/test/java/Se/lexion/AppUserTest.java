package Se.lexion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppUserTest {

    @Test
    void testAppUserCreation() {
        AppUser user = new AppUser("john123", "password123", AppRole.ROLE_APP_USER);
        assertEquals("john123", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals(AppRole.ROLE_APP_USER, user.getRole());
    }

    @Test
    void testNullUsername() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AppUser(null, "password", AppRole.ROLE_APP_USER);
        });
    }

    @Test
    void testEmptyUsername() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AppUser("", "password", AppRole.ROLE_APP_USER);
        });
    }

    @Test
    void testNullPassword() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AppUser("username", null, AppRole.ROLE_APP_USER);
        });
    }

    @Test
    void testEmptyPassword() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AppUser("username", "", AppRole.ROLE_APP_USER);
        });
    }

    @Test
    void testNullRole() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AppUser("username", "password", null);
        });
    }

    @Test
    void testEqualsExcludesPassword() {
        AppUser user1 = new AppUser("john123", "password1", AppRole.ROLE_APP_USER);
        AppUser user2 = new AppUser("john123", "password2", AppRole.ROLE_APP_USER);

        assertEquals(user1, user2); // Should be equal despite different passwords
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testToStringExcludesPassword() {
        AppUser user = new AppUser("john123", "secretpassword", AppRole.ROLE_APP_ADMIN);
        String result = user.toString();

        assertTrue(result.contains("john123"));
        assertTrue(result.contains("ROLE_APP_ADMIN"));
        assertFalse(result.contains("secretpassword")); // Password should not be included
    }

    @Test
    void testAppRoleValues() {
        assertEquals(2, AppRole.values().length);
        assertEquals(AppRole.ROLE_APP_USER, AppRole.valueOf("ROLE_APP_USER"));
        assertEquals(AppRole.ROLE_APP_ADMIN, AppRole.valueOf("ROLE_APP_ADMIN"));
    }
}