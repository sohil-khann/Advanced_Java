import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Component
@interface Component {}

@Retention(RetentionPolicy.RUNTIME)
@Service
@interface Service {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Autowired {}

class CircularDependencyException extends RuntimeException {
    public CircularDependencyException(String msg) { super(msg); }
}

class ApplicationContext {
    private final Map<String, Object> beans = new HashMap<>();
    private final Map<String, Class<?>> definitions = new HashMap<>();
    private final Set<String> creating = new HashSet<>();

    public void scan(String... packages) {
        for (String pkg : packages) {
            for (Class<?> clazz : Reflections.findAllClasses(pkg)) {
                if (clazz.isAnnotationPresent(Component.class) || clazz.isAnnotationPresent(Service.class)) {
                    String name = lowerFirst(clazz.getSimpleName());
                    definitions.put(name, clazz);
                }
            }
        }
    }

    public void scan(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            for (Class<?> nested : clazz.getDeclaredClasses()) {
                if (Modifier.isStatic(nested.getModifiers()) &&
                    (nested.isAnnotationPresent(Component.class) || nested.isAnnotationPresent(Service.class))) {
                    String name = lowerFirst(nested.getSimpleName());
                    definitions.put(name, nested);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> type) {
        String name = lowerFirst(type.getSimpleName());
        return (T) getBean(name);
    }

    public Object getBean(String name) {
        if (!beans.containsKey(name)) {
            if (creating.contains(name)) {
                throw new CircularDependencyException("Circular dependency detected: " + name);
            }
            creating.add(name);
            Class<?> clazz = definitions.get(name);
            if (clazz == null) {
                throw new IllegalArgumentException("No bean definition found: " + name);
            }
            try {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                inject(instance);
                beans.put(name, instance);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException("Failed to instantiate: " + name, e);
            } finally {
                creating.remove(name);
            }
        }
        return beans.get(name);
    }

    private void inject(Object target) throws IllegalAccessException, InvocationTargetException {
        for (Field field : target.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                String depName = lowerFirst(field.getType().getSimpleName());
                Object dep = getBean(depName);
                field.set(target, dep);
            }
        }
    }

    private static String lowerFirst(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }
}

class Reflections {
    static List<Class<?>> findAllClasses(String pkg) {
        List<Class<?>> list = new ArrayList<>();
        String path = pkg.replace('.', '/');
        try {
            java.net.URL url = Thread.currentThread().getContextClassLoader().getResource(path);
            if (url == null) return list;
            java.io.File dir = new java.io.File(url.getFile());
            if (!dir.exists() || !dir.isDirectory()) return list;
            for (java.io.File f : dir.listFiles()) {
                String name = f.getName();
                if (name.endsWith(".class")) {
                    String cls = pkg + "." + name.substring(0, name.length() - 6);
                    list.add(Class.forName(cls));
                }
            }
        } catch (Exception e) {
            System.err.println("Scan error: " + e.getMessage());
        }
        return list;
    }
}

class MiniDIFramework {
    @Component
    public static class MessageRepository {
        public String getMessage() { return "Hello from MessageRepository"; }
    }

    @Service
    public static class MessageService {
        @Autowired
        private MessageRepository repository;

        public String getMessage() { return repository.getMessage(); }
    }

    @Service
    public static class NotificationService {
        @Autowired
        private MessageService service;

        public String sendNotification() { return "Notification: " + service.getMessage(); }
    }

    @Service
    public static class ServiceA {
        @Autowired
        private ServiceB serviceB;

        public String getName() { return "ServiceA"; }
    }

    @Service
    public static class ServiceB {
        @Autowired
        private ServiceA serviceA;

        public String getName() { return "ServiceB"; }
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ApplicationContext();
        ctx.scan(MiniDIFramework.class);

        MessageService service = ctx.getBean(MessageService.class);
        System.out.println(service.getMessage());

        NotificationService notifier = ctx.getBean(NotificationService.class);
        System.out.println(notifier.sendNotification());

        System.out.println("All beans injected successfully!");

        System.out.println("Testing circular dependency detection:");
        try {
            ctx.getBean(ServiceA.class);
        } catch (CircularDependencyException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
