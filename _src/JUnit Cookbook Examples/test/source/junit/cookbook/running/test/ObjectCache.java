package junit.cookbook.running.test;

import java.util.*;

import junit.cookbook.running.Directory;


public class ObjectCache {
    public static Directory directory;

    private static int cacheHits = 0;
    private static Map cache = new HashMap();

    public static int countCacheHits() {
        return cacheHits;
    }

    public static Object lookup(String name) {
        Object value;
        if (cache.containsKey(name)) {
            value = cache.get(name);
            cacheHits++;
        }
        else {
            value = directory.get(name);
            cache.put(name, value);
        }
        return value;
    }
}