import java.lang.annotation.*;
import java.util.*;
import java.util.concurrent.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Cacheable {
    String cacheName();
    long ttl();
}

class CacheEntry {
    Object value;
    long expiryTime;
    CacheEntry(Object value, long ttlMillis) {
        this.value = value;
        this.expiryTime = System.currentTimeMillis() + ttlMillis;
    }
    boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

class CacheManager {
    private static final Map<String, Map<String, CacheEntry>> cache = new ConcurrentHashMap<>();
    public static void put(String cacheName, String key, Object value, long ttlMillis) {
        cache.computeIfAbsent(cacheName, k -> new ConcurrentHashMap<>()).put(key, new CacheEntry(value, ttlMillis));
    }
    public static Object get(String cacheName, String key) {
        Map<String, CacheEntry> map = cache.get(cacheName);
        if (map == null) return null;
        CacheEntry entry = map.get(key);
        if (entry == null || entry.isExpired()) {
            if (entry != null) map.remove(key);
            return null;
        }
        return entry.value;
    }
}

class CachingAnnotation3 {
    public static void main(String[] args) {
        Service service = new Service();
        System.out.println(service.cachedOperation("key1"));
        System.out.println(service.cachedOperation("key1"));
    }
}

class Service {
    @Cacheable(cacheName = "main", ttl = 5000)
    public String expensiveOperation(String key) {
        System.out.println("Computing value for: " + key);
        return "Value-" + key;
    }
    public String cachedOperation(String key) {
        try {
            java.lang.reflect.Method m = getClass().getDeclaredMethod("expensiveOperation", String.class);
            Cacheable c = m.getAnnotation(Cacheable.class);
            if (c != null) {
                Object cached = CacheManager.get(c.cacheName(), key);
                if (cached != null) return (String) cached;
                String result = expensiveOperation(key);
                CacheManager.put(c.cacheName(), key, result, c.ttl());
                return result;
            }
        } catch (NoSuchMethodException e) { e.printStackTrace(); }
        return expensiveOperation(key);
    }
}
