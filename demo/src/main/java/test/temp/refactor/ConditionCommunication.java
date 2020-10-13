package test.temp.refactor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shane
 *         condition 比普通的锁，最先进的一点是，可以有多重条件，主要用于实现阻塞队列。
 */

public class ConditionCommunication {

    public static void main(String[] args) {
        final Business business = new Business();

        new Thread(() -> {
            for (int i = 1; i <= 50; i++) {
                business.sub(i);
            }

        }).start();

        for (int i = 1; i <= 50; i++) {
            business.main(i);

        }

    }


    static class Business {

        Lock lock = new ReentrantLock();

        // condition 依赖于lock而产生

        Condition condition = lock.newCondition();

        private boolean bShouldSub = true;

        void sub(int i) {

            lock.lock();

            try {

                while (!bShouldSub) {
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


                for (int j = 1; j <= 10; j++) {
                    System.out.println("sub thread sequence of " + j + ", loop of " + i);
                }

                bShouldSub = false;
                condition.signal();

            } finally {
                lock.unlock();
            }

        }


        public void main(int i) {

            lock.lock();
            try {
                while (bShouldSub) {
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


                for (int j = 1; j <= 100; j++) {
                    System.out.println("main thread sequence of " + j + ", loop of " + i);
                }

                bShouldSub = true;
                condition.signal();

            } finally {
                lock.unlock();
            }

        }

    }

}
