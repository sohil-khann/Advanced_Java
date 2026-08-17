import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface AdminOnly {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface UserOnly {}

enum Role { ADMIN, USER }

class DocumentService {
    @AdminOnly
    public void deleteDocument() {
        System.out.println("Document deleted");
    }

    @UserOnly
    public void viewDocument() {
        System.out.println("Viewing document");
    }
}

public class RoleBasedAccess {
    public static void main(String[] args) {
        DocumentService service = new DocumentService();
        Role currentRole = Role.USER;

        for (Method method : DocumentService.class.getDeclaredMethods()) {
            boolean adminOnly = method.isAnnotationPresent(AdminOnly.class);
            boolean userOnly = method.isAnnotationPresent(UserOnly.class);

            if (adminOnly && currentRole == Role.ADMIN) {
                invokeSafely(method, service);
            } else if (userOnly && currentRole == Role.USER) {
                invokeSafely(method, service);
            } else if (adminOnly || userOnly) {
                System.out.println("Access denied to method: " + method.getName());
            }
        }
    }

    private static void invokeSafely(Method method, Object instance) {
        try {
            method.invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
