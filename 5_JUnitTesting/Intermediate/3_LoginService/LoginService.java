import java.util.HashMap;
import java.util.Map;

public class LoginService {

    private Map<String, String> users;
    private Map<String, Boolean> lockedAccounts;

    public LoginService() {
        users = new HashMap<>();
        lockedAccounts = new HashMap<>();
    }

    public void addUser(String username, String password) {
        users.put(username, password);
        lockedAccounts.putIfAbsent(username, false);
    }

    public boolean login(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (lockedAccounts.getOrDefault(username, false)) {
            throw new IllegalStateException("Account is locked");
        }
        if (!users.containsKey(username)) {
            throw new IllegalArgumentException("User not found");
        }
        return users.get(username).equals(password);
    }

    public void lockAccount(String username) {
        lockedAccounts.put(username, true);
    }

    public boolean validateCredentials(String username, String password) {
        try {
            return login(username, password);
        } catch (Exception e) {
            return false;
        }
    }
}
