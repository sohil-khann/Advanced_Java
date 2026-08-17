import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@interface Executable {}

class Job {
    @Executable
    public void start() {
        System.out.println("Job started.");
    }

    @Executable
    public void process() {
        System.out.println("Job processed.");
    }

    public void skip() {
        System.out.println("Skipped.");
    }
}

public class _5_MarkExecutable {
    public static void main(String[] args) throws Exception {
        Job job = new Job();
        Class<?> clazz = Job.class;

        System.out.println("Scanning for @Executable methods...");
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Executable.class)) {
                System.out.println("Running: " + method.getName());
                method.invoke(job);
            }
        }
    }
}
