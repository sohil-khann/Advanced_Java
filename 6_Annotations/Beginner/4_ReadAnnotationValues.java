import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@interface Executable {
    int priority();
    String description();
}

class Service {
    @Executable(priority = 1, description = "Initialize core system")
    public void init() {
        System.out.println("System initialized.");
    }

    @Executable(priority = 3, description = "Cleanup temporary files")
    public void cleanup() {
        System.out.println("Temporary files cleaned.");
    }

    public void helper() {
        System.out.println("Helper executed.");
    }
}

public class _4_ReadAnnotationValues {
    public static void main(String[] args) throws Exception {
        Service service = new Service();
        Class<?> clazz = Service.class;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Executable.class)) {
                Executable ex = method.getAnnotation(Executable.class);
                System.out.println("Method: " + method.getName());
                System.out.println("  Priority    : " + ex.priority());
                System.out.println("  Description : " + ex.description());
                method.invoke(service);
                System.out.println();
            }
        }
    }
}
