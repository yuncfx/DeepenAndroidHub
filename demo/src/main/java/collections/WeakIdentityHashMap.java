package collections;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implements a combination of WeakHashMap and IdentityHashMap. Useful for
 * caches that need to key off of a == comparison instead of a .equals.
 * 
 * <b> This class is not a general-purpose Map implementation! While this class
 * implements the Map interface, it intentionally violates Map's general
 * contract, which mandates the use of the equals method when comparing objects.
 * This class is designed for use only in the rare cases wherein
 * reference-equality semantics are required.
 * 
 * Note that this implementation is not synchronized. </b>
 */
public class WeakIdentityHashMap<K, V> implements Map<K, V> {
    /**
     * Reference queues, to which registered reference objects are appended by
     * the garbage collector after the appropriate reachability changes are
     * detected. 
     * 中文JavaDoc的描述：引用队列，在检测到适当的可到达性更改后，垃圾回收器将已注册的引用对象添加到该队列中
     */
    private final ReferenceQueue<K> queue = new ReferenceQueue<K>();
    private Map<IdentityWeakReference, V> backingStore = new HashMap<IdentityWeakReference, V>();

    public WeakIdentityHashMap() {
    }

    public void clear() {
        backingStore.clear();
        reap();
    }

    public boolean containsKey(Object key) {
        reap();
        return backingStore.containsKey(new IdentityWeakReference(key));
    }

    public boolean containsValue(Object value) {
        reap();
        return backingStore.containsValue(value);
    }

    public Set<Entry<K, V>> entrySet() {
        reap();
        Set<Entry<K, V>> ret = new HashSet<Entry<K, V>>();
        for (Entry<IdentityWeakReference, V> ref : backingStore.entrySet()) {
            final K key = ref.getKey().get();
            final V value = ref.getValue();
            Entry<K, V> entry = new Entry<K, V>() {
                public K getKey() {
                    return key;
                }

                public V getValue() {
                    return value;
                }

                public V setValue(V value) {
                    throw new UnsupportedOperationException();
                }
            };
            ret.add(entry);
        }
        return Collections.unmodifiableSet(ret);
    }

    public Set<K> keySet() {
        reap();
        Set<K> ret = new HashSet<K>();
        for (IdentityWeakReference ref : backingStore.keySet()) {
            ret.add(ref.get());
        }
        return Collections.unmodifiableSet(ret);
    }

    public boolean equals(Object o) {
        return backingStore.equals(((WeakIdentityHashMap) o).backingStore);
    }

    public V get(Object key) {
        reap();
        return backingStore.get(new IdentityWeakReference(key));
    }

    public V put(K key, V value) {
        reap();
        return backingStore.put(new IdentityWeakReference(key), value);
    }

    public int hashCode() {
        reap();
        return backingStore.hashCode();
    }

    public boolean isEmpty() {
        reap();
        return backingStore.isEmpty();
    }

    public void putAll(Map t) {
        throw new UnsupportedOperationException();
    }

    public V remove(Object key) {
        reap();
        return backingStore.remove(new IdentityWeakReference(key));
    }

    public int size() {
        reap();
        return backingStore.size();
    }

    public Collection<V> values() {
        reap();
        return backingStore.values();
    }

    /**
     * WeakHashMap的实现也是通过ReferenceQueue这个“监听器”来优雅的实现自动删除那些引用不可达的key的
     * 
     * 这个方法就是每次要遍历queue队列，如果queue队列不为空，则删除掉backingStore里面的对应的元素，因为该元素已经不可达
     */
    private synchronized void reap() {
        Object zombie = queue.poll();

        while (zombie != null) {
            IdentityWeakReference victim = (IdentityWeakReference) zombie;
            backingStore.remove(victim);
            zombie = queue.poll();
        }
    }

    class IdentityWeakReference extends WeakReference<K> {
        int hash;

        @SuppressWarnings("unchecked")
        IdentityWeakReference(Object obj) {
            super((K) obj, queue);
            hash = System.identityHashCode(obj);
        }

        public int hashCode() {
            return hash;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            IdentityWeakReference ref = (IdentityWeakReference) o;
            if (this.get() == ref.get()) {
                return true;
            }
            return false;
        }
    }
}