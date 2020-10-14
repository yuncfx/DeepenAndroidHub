package concurrency.multithread;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * @author shane
 */
public class BoundedHashSet<T> {
    private final Set<T> mSet;
    private final Semaphore mSemaphore;

    public BoundedHashSet(int bound) {
        this.mSet = Collections.synchronizedSet(new HashSet<>());
        mSemaphore = new Semaphore(bound);
    }

    public boolean add(T t) throws InterruptedException {
        mSemaphore.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = mSet.add(t);
            return wasAdded;
        } finally {
            if (! wasAdded) {
                mSemaphore.release();
            }
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = mSet.remove(o);
        if (wasRemoved) {
            mSemaphore.release();
        }

        return wasRemoved;
    }
}
