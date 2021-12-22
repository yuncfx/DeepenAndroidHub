package design.pattern.lg;


import java.util.HashMap;
import java.util.Map;

/**
 * 线程内唯一，实际并不是单例模式。
 */
public class ThreadSingleton {
    private static final ThreadLocal<ThreadSingleton> local = new ThreadLocal<>();
    private Map<String, Object> data = new HashMap<>();

    public Map<String, Object> getData() {
        return getSingleton().data;
    }

    public void setData(Map<String, Object> data) {
        getSingleton().data.putAll(data);
    }

    public void setData(String key, String value) {
        getSingleton().data.put(key, value);
    }

    public Object get(String key) {
        return getSingleton().data.get(key);
    }

    private static ThreadSingleton init() {
        ThreadSingleton threadSingleton = new ThreadSingleton();
        local.set(threadSingleton);
        return threadSingleton;
    }

    public static ThreadSingleton getSingleton() {
        ThreadSingleton threadSingleton = local.get();
        if (null == threadSingleton) {
            threadSingleton = init();
        }
        return threadSingleton;
    }

    public static void remove() {
        local.remove();
    }
}
