import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Service {}

@Service
class PaymentService {
    public void processPayment() {
        System.out.println("Payment processed");
    }
}

@Service
class NotificationService {
    public void sendNotification() {
        System.out.println("Notification sent");
    }
}

class ServiceRegistry {
    private List<Object> services = new ArrayList<>();

    public <T> void register(Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            services.add(instance);
            System.out.println("Registered: " + clazz.getSimpleName());
        } catch (Exception e) {
            System.out.println("Failed to register: " + clazz.getSimpleName());
        }
    }
}

public class AutoRegisterService {
    public static void main(String[] args) {
        ServiceRegistry registry = new ServiceRegistry();
        Class<?>[] serviceClasses = { PaymentService.class, NotificationService.class };

        for (Class<?> clazz : serviceClasses) {
            if (clazz.isAnnotationPresent(Service.class)) {
                registry.register(clazz);
            }
        }
    }
}
