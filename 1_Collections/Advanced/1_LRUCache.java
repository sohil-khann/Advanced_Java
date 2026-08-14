import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU (Least Recently Used) Cache implementation using LinkedHashMap.
 * Supports configurable capacity with get and put operations.
 */
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    /**
     * Constructs an LRU Cache with the specified capacity.
     *
     * @param capacity the maximum number of entries the cache can hold
     */
    public LRUCache(int capacity) {
        super(capacity, 0.75f, true); // accessOrder = true for LRU behavior
        this.capacity = capacity;
    }

    /**
     * Returns true when the eldest entry should be removed.
     * This method is called after put/putAll operations.
     *
     * @param eldest the least recently accessed entry
     * @return true if the eldest entry should be evicted
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    /**
     * Retrieves a value by key. If the key exists, it becomes the most recently used.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or null if not present
     */
    @Override
    public V get(Object key) {
        return super.get(key);
    }

    /**
     * Associates the specified value with the specified key. If the cache is full,
     * the least recently used entry is evicted.
     *
     * @param key   the key with which the value is to be associated
     * @param value the value to be associated with the key
     * @return the previous value associated with the key, or null if none
     */
    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }

    /**
     * Demonstrates the LRU Cache functionality.
     */
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        System.out.println("Adding entries: (1, Diya), (2, Karan), (3, Meera)");
        cache.put(1, "Diya");
        cache.put(2, "Karan");
        cache.put(3, "Meera");

        System.out.println("Cache after initial adds: " + cache);

        System.out.println("\nAccessing key 1 (makes it most recently used): " + cache.get(1));

        System.out.println("\nAdding entry (4, Aditya) - should evict least recently used (key 2)");
        cache.put(4, "Aditya");

        System.out.println("Cache after adding 4: " + cache);
        System.out.println("Key 2 removed? " + !cache.containsKey(2));

        System.out.println("\nAdding entry (5, Saanvi) - should evict key 3");
        cache.put(5, "Saanvi");

        System.out.println("Cache after adding 5: " + cache);
        System.out.println("Key 3 removed? " + !cache.containsKey(3));
    }
}
