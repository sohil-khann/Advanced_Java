import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Loggable {}

class Calculator {
    @Loggable
    public int multiply(int a, int b) {
        try { Thread.sleep(20); } catch (InterruptedException ignored) {}
        return a * b;
    }

    public int divide(int a, int b) {
        return a / b;
    }
}

public class LoggingAnnotation {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        for (Method method : Calculator.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Loggable.class)) {
                long start = System.currentTimeMillis();
                try {
                    method.invoke(calc, 6, 7);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                long end = System.currentTimeMillis();
                System.out.println("Method: " + method.getName() + ", Execution time: " + (end - start) + "ms");
            }
        }
    }
}
