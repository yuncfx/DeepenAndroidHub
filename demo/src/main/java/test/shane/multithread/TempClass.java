package test.shane.multithread;

import org.junit.Test;

import java.lang.management.ThreadMXBean;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shane
 */
public class TempClass {
    private volatile String name;
    public static void main(String[] args) {
        String str = "book";
        System.out.println(str.hashCode());
        ArrayDeque arrayDeque = new ArrayDeque<>();
        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();
        BlockingQueue<String> strings = new LinkedBlockingQueue<>();
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(10, (ThreadFactory) Thread::new);

        String s = "";
        System.out.println(Long.valueOf(s));
    }

    @Test
    public void test() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM", Locale.getDefault());


        long l = (long) (-3.69295*Math.pow(10, 6));
        System.out.println(l);

        ReentrantLock lock = new ReentrantLock(false);

        Semaphore semaphore = new Semaphore(5, false);

        AtomicReferenceFieldUpdater<TempClass, String> updater = AtomicReferenceFieldUpdater.newUpdater(TempClass.class, String.class, "name");
        TempClass tempClass = new TempClass();
        updater.compareAndSet(tempClass, null, "hello world");
        System.out.println(tempClass.name);

        String str = sdf.format(calendar.getTime());
        System.out.println(str);

    }

    public static Object getLast(final Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
    }

    public static void deleteLast(final Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }

}

class SynchronizedExample {
    int a = 0;
    boolean flag = false;

    public synchronized void writer() { // 获取锁
        a = 1;
        flag = true;
    } // 释放锁

    public synchronized void reader() { // 获取锁
        if (flag) {
            int i = a;
        } // 释放锁
    }
}


