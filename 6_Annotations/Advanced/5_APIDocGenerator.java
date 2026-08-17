import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface ApiDoc {
    String summary();
    String description() default "";
    String returns();
}

class ApiService {

    @ApiDoc(summary = "Get user by ID", description = "Fetches a user from the database using their unique identifier.", returns = "User")
    public User getUser(int id) {
        return new User(id, "user" + id, "user" + id + "@example.com");
    }

    @ApiDoc(summary = "Create user", description = "Creates a new user record.", returns = "boolean")
    public boolean createUser(String username, String email) {
        return true;
    }

    @ApiDoc(summary = "Delete user", description = "Deletes a user by ID.", returns = "boolean")
    public boolean deleteUser(int id) {
        return true;
    }
}

class User {
    public int id;
    public String username;
    public String email;

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}

class ApiDocGenerator {

    public static String generateMarkdown(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(clazz.getSimpleName()).append(" API Documentation\n\n");

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(ApiDoc.class)) {
                ApiDoc doc = method.getAnnotation(ApiDoc.class);
                sb.append("## ").append(method.getName()).append("\n\n");
                sb.append("**Summary:** ").append(doc.summary()).append("\n\n");
                if (!doc.description().isEmpty()) {
                    sb.append("**Description:** ").append(doc.description()).append("\n\n");
                }
                sb.append("**Returns:** `").append(doc.returns()).append("`\n\n");
                sb.append("---\n\n");
            }
        }
        return sb.toString();
    }
}

public class APIDocGenerator {
    public static void main(String[] args) {
        String markdown = ApiDocGenerator.generateMarkdown(ApiService.class);
        System.out.println(markdown);
    }
}
