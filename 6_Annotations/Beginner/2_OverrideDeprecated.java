import java.lang.annotation.*;
import java.lang.reflect.*;

class BaseProcessor {
    @Deprecated
    public void oldProcess(String data) {
        System.out.println("Old process running with: " + data);
    }

    public void newProcess(String data) {
        System.out.println("New process running with: " + data);
    }
}

class AdvancedProcessor extends BaseProcessor {
    @Override
    public void newProcess(String data) {
        System.out.println("Advanced process running with: " + data);
    }
}

public class _2_OverrideDeprecated {
    public static void main(String[] args) throws Exception {
        AdvancedProcessor processor = new AdvancedProcessor();
        Class<?> clazz = AdvancedProcessor.class;

        System.out.println("--- Method annotations ---");
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Deprecated.class)) {
                System.out.println("Method " + method.getName() + " is @Deprecated");
            }
            if (method.isAnnotationPresent(Override.class)) {
                System.out.println("Method " + method.getName() + " is @Override");
            }
        }

        System.out.println("\n--- Invoking methods ---");
        processor.oldProcess("legacy-data");
        processor.newProcess("modern-data");
    }
}
