package test.temp.refactor;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author shane
 *         读写锁的妙用- 自己写一个缓存系统
 */
public class CacheDemo {

    private Map<String, Object> cache = new HashMap<String, Object>();

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    // 也可以单纯用synchronized关键字进行同步

    public Object getData(String key) {
        rwl.readLock().lock();
        Object object;

        try {
            object = cache.get(key);
            if (null == object) {
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    // 重复校验
                    if (null == object) {
                        // queryDB()
                        object = "aaaaa";
                    }

                } finally {
                    rwl.writeLock().unlock();
                    rwl.readLock().lock();
                }

            }

        } finally {
            rwl.readLock().unlock();
        }

        return object;

    }

}