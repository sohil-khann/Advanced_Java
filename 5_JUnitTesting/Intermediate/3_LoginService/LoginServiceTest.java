import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private final LoginService loginService = new LoginService();

    @Test
    void testValidLogin() {
        loginService.addUser("admin", "password123");
        assertTrue(loginService.login("admin", "password123"));
    }

    @Test
    void testInvalidPassword() {
        loginService.addUser("admin", "password123");
        assertThrows(IllegalArgumentException.class, () -> loginService.login("admin", "wrong"));
    }

    @Test
    void testEmptyUsername() {
        assertThrows(IllegalArgumentException.class, () -> loginService.login("", "password"));
    }

    @Test
    void testEmptyPassword() {
        loginService.addUser("admin", "password123");
        assertThrows(IllegalArgumentException.class, () -> loginService.login("admin", ""));
    }

    @Test
    void testNullUsername() {
        assertThrows(IllegalArgumentException.class, () -> loginService.login(null, "password"));
    }

    @Test
    void testLockedAccount() {
        loginService.addUser("admin", "password123");
        loginService.lockAccount("admin");
        assertThrows(IllegalStateException.class, () -> loginService.login("admin", "password123"));
    }

    @Test
    void testUserNotFound() {
        assertThrows(IllegalArgumentException.class, () -> loginService.login("unknown", "password"));
    }

    @Test
    void testValidateCredentialsReturnsFalseForInvalid() {
        loginService.addUser("admin", "password123");
        assertFalse(loginService.validateCredentials("admin", "wrong"));
    }

    @Test
    void testValidateCredentialsReturnsTrueForValid() {
        loginService.addUser("admin", "password123");
        assertTrue(loginService.validateCredentials("admin", "password123"));
    }
}
