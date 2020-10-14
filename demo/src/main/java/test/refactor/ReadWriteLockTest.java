package test.refactor;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author shane
 *         <p>
 *         读写锁
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {

        final Queue3 q = new Queue3();

        for (int i = 0; i < 3; i++) {

            new Thread(() -> {
                while (true) {
                    q.get();
                }

            }).start();

            new Thread(() -> {

                while (true) {
                    q.put(new Random().nextInt(10000));
                }

            }).start();

        }

    }

}

class Queue3 {

    // 共享数据，只能有一个线程能访问

    private Object data = null;
    ReadWriteLock rwl = new ReentrantReadWriteLock();

    public void get() {

        rwl.readLock().lock();

        try {

            System.out.println(Thread.currentThread().getName()
                    + "be ready to read data!");

            Thread.sleep((long) (new Random().nextInt(10000)));

            System.out.println(Thread.currentThread().getName()
                    + "have read data : " + data);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }

    }


    public void put(Object data) {
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()
                    + " be ready to write data!");
            Thread.sleep((long) (new Random().nextInt(10000)));

            this.data = data;

            System.out.println(Thread.currentThread().getName()
                    + " have write data : " + data);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }

    }

}
