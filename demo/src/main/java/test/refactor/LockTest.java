package test.refactor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shane
 *         使用Lock锁实现线程同步通信
 */
public class LockTest {

    public static void main(String[] args) {
        new LockTest().init();
    }

    private void init() {
        final Outputer outputer = new Outputer();
        new Thread(() -> {

            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                outputer.output("zhangxiaoxiang");
            }

        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                outputer.output("lihuoming");

            }

        }).start();

    }


    class Outputer {

        Lock lock = new ReentrantLock();

        public void output(String name) {
            int len = name.length();
            lock.lock();
            try {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }

                System.out.println();

            } finally {
                lock.unlock();
            }
        }

        public synchronized void output2(String name) {
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }

            System.out.println();

        }

    }

}
