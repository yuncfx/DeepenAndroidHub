package collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Utility class for cache map.
 */
public class CacheMap extends TreeMap<String, Object> {
    private static final long serialVersionUID = 6681131647931821052L;

    private final int maxSize;
    private final int evictSize;

    private final LinkedList<Object> accessList = new LinkedList<Object>();

    /**
     * Constructor of cache map. If the map exceed the maximum size, the
     * key/value sets will be removed from map based on specified evict size.
     * 
     * @param maxSize
     *            maximum size of the map
     * @param evictSize
     *            number of evict object
     */
    public CacheMap(int maxSize, int evictSize) {
        this.maxSize = maxSize;
        this.evictSize = evictSize;
    }

    private void evict() {
        Iterator<Object> it = accessList.iterator();
        for (int i = 0; i < evictSize; i++) {
            if (!it.hasNext())
                return;
            Object key = it.next();
            this.remove(key);
            it.remove();
        }
    }

    private int searchAccessList(Object key) {
        return accessList.indexOf(key);
    }

    private void accessEntry(Object key) {
        int idx = searchAccessList(key);
        if (idx >= 0) {
            accessList.remove(idx);
        }
        accessList.add(key);
    }

    public Object put(String key, Object val) {
        if (size() >= maxSize)
            evict();
        accessEntry(key);
        return super.put(key, val);
    }

    public Object get(Object key) {
        accessEntry(key);
        return super.get(key);
    }

    /**
     * Search a key that starts with the specified prefix from the map, and
     * return the value corresponding to the key.
     * 
     * @param prefix
     *            target prefix
     * @return the value whose key starts with prefix, or null if not available
     */
    public Object matchStartsWith(String prefix) {
        SortedMap<String, Object> smap = super.tailMap(prefix);
        Object okey;
        try {
            okey = smap.firstKey();
        } catch (NoSuchElementException e) {
            return null;
        }
        if (!(okey instanceof String))
            return null;
        String key = (String) okey;
        // System.err.println("MSW:" + key + " / " + prefix);
        if (!key.startsWith(prefix))
            return null;
        return super.get(key);
    }
}