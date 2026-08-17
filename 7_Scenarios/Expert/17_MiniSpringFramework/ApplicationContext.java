import java.lang.reflect.Field;
import java.util.*;

public class ApplicationContext {
    private final Map<String, Object> beans = new HashMap<>();

    public void scan(String basePackage) {
        try {
            for (Object obj : getClassesForPackage(basePackage)) {
                Class<?> cls = obj.getClass();
                if (cls.isAnnotationPresent(Component.class) || cls.isAnnotationPresent(Service.class)) {
                    String beanName = cls.getSimpleName().substring(0, 1).toLowerCase() + cls.getSimpleName().substring(1);
                    beans.put(beanName, obj);
                }
            }

            for (Object bean : beans.values()) {
                for (Field field : bean.getClass().getDeclaredFields()) {
                    if (field.isAnnotationPresent(Autowired.class)) {
                        field.setAccessible(true);
                        String depName = field.getType().getSimpleName().substring(0, 1).toLowerCase() + field.getType().getSimpleName().substring(1);
                        Object dependency = beans.get(depName);
                        if (dependency != null) {
                            field.set(bean, dependency);
                        } else {
                            System.out.println("Warning: Could not autowire dependency for " + field.getName());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Context scan error: " + e.getMessage());
        }
    }

    public Object getBean(String name) {
        return beans.get(name);
    }

    @SuppressWarnings("unchecked")
    private List<Object> getClassesForPackage(String pkg) throws Exception {
        String path = pkg.replace('.', '/');
        java.net.URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        if (url == null) return Collections.emptyList();

        java.io.File dir = new java.io.File(url.getFile());
        List<Object> instances = new ArrayList<>();
        for (String file : dir.list()) {
            if (file.endsWith(".class")) {
                String className = pkg + "." + file.replace(".class", "");
                Class<?> cls = Class.forName(className);
                if (!cls.isInterface() && !java.lang.reflect.Modifier.isAbstract(cls.getModifiers())) {
                    instances.add(cls.getDeclaredConstructor().newInstance());
                }
            }
        }
        return instances;
    }
}
