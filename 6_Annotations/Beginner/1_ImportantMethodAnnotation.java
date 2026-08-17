import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@interface Important {}

class Task {
    @Important
    public void criticalBackup() {
        System.out.println("Running critical backup...");
    }

    public void routineLog() {
        System.out.println("Running routine log...");
    }

    @Important
    public void securityPatch() {
        System.out.println("Applying security patch...");
    }
}

public class _1_ImportantMethodAnnotation {
    public static void main(String[] args) throws Exception {
        Task task = new Task();
        Class<?> clazz = Task.class;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Important.class)) {
                System.out.println("Invoking @Important method: " + method.getName());
                method.invoke(task);
            }
        }
    }
}
