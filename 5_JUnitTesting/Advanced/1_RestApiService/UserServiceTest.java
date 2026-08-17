import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    void testCreateUser() {
        UserService.User user = userService.createUser("Priya", "priya@example.com");
        assertNotNull(user);
        assertEquals("Priya", user.getName());
        assertEquals("priya@example.com", user.getEmail());
    }

    @Test
    void testFindUser() {
        UserService.User created = userService.createUser("Rahul", "rahul@example.com");
        UserService.User found = userService.findUser(created.getId());
        assertNotNull(found);
        assertEquals("Rahul", found.getName());
    }

    @Test
    void testFindNonExistentUser() {
        assertNull(userService.findUser(999));
    }

    @Test
    void testDeleteUser() {
        UserService.User created = userService.createUser("Arjun", "arjun@example.com");
        boolean deleted = userService.deleteUser(created.getId());
        assertTrue(deleted);
        assertNull(userService.findUser(created.getId()));
    }

    @Test
    void testDeleteNonExistentUser() {
        boolean deleted = userService.deleteUser(999);
        assertFalse(deleted);
    }

    @Test
    void testCreateUserWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> userService.createUser("", "email@test.com"));
    }

    @Test
    void testCreateUserWithNullEmail() {
        assertThrows(IllegalArgumentException.class, () -> userService.createUser("Name", null));
    }

    @Test
    void testGetAllUsers() {
        userService.createUser("User1", "u1@test.com");
        userService.createUser("User2", "u2@test.com");
        assertEquals(2, userService.getAllUsers().size());
    }
}
