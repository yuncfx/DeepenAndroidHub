package test.temp.refactor;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author shane
 *         condition 比普通的锁，最先进的一点是，可以有多重条件，主要用于实现阻塞队列。
 *         <p>
 *         老大做完老二做，老二做完老三做，老三做完再老大做， 循环阻塞执行。
 */

public class ThreeConditionCommunication {

    public static void main(String[] args) {

        final Business business = new Business();

        new Thread(() -> {
            for (int i = 1; i <= 50; i++) {
                business.sub2(i);
            }

        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 50; i++) {
                business.sub3(i);
            }

        }).start();


        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }

    }


    static class Business {

        Lock lock = new ReentrantLock();

        // condition 依赖于lock而产生

        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        private int shouldStatus = 1;

        void sub2(int i) {

            lock.lock();

            try {

                while (shouldStatus != 2) {
                    try {
                        condition2.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


                for (int j = 1; j <= 10; j++) {
                    System.out.println("sub2 thread sequence of " + j + ", loop of " + i);

                }

                shouldStatus = 3;
                condition3.signal();

            } finally {
                lock.unlock();
            }

        }


        void sub3(int i) {

            lock.lock();

            try {
                while (shouldStatus != 3) {
                    try {
                        condition3.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


                for (int j = 1; j <= 20; j++) {
                    System.out.println("sub3 thread sequence of " + j + ", loop of " + i);
                }

                shouldStatus = 1;
                condition1.signal();

            } finally {
                lock.unlock();
            }

        }


        public void main(int i) {

            lock.lock();
            try {
                while (shouldStatus != 1) {
                    try {
                        condition1.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                for (int j = 1; j <= 100; j++) {
                    System.out.println("main thread sequence of " + j + ", loop of " + i);
                }

                shouldStatus = 2;
                condition2.signal();

            } finally {

                lock.unlock();

            }

        }

    }

}