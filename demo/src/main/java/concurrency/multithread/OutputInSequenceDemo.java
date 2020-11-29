package concurrency.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 3个线程A， B， C， 以A，B, C的顺序输出结果
 */
public class OutputInSequenceDemo {
    private volatile int current = 0;
    int countA = 0;
    int countB = 0;
    int countC = 0;

    private static final int TIMES = 1000;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition conditionA = lock.newCondition();
    private final Condition conditionB = lock.newCondition();
    private final Condition conditionC = lock.newCondition();

    Runnable rA = new Runnable() {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < TIMES; i++) {
                    if (current == 0) {
                        System.out.println("thread-a countA:" + (countA++));
                        current = 1;
                        conditionB.signal();
                        if (i < TIMES - 1) {
                            conditionA.await();
                        }
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    };

    Runnable rB = new Runnable() {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < TIMES; i++) {
                    if (current == 1) {
                        System.out.println("thread-b countB:" + (countB++));
                        current = 2;
                        conditionC.signal();
                        if (i < TIMES -1 ) {
                            conditionB.await();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    };

    Runnable rC = new Runnable() {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < TIMES; i++) {
                    if (current == 2) {
                        System.out.println("thread-c countC:" + (countC++));
                        current = 0;
                        conditionA.signal();
                        if (i < TIMES - 1 ) {
                            conditionC.await();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    };

    Thread a = new Thread(rA);
    Thread b = new Thread(rB);
    Thread c = new Thread(rC);

    public static void main(String[] args) {
        OutputInSequenceDemo demo = new OutputInSequenceDemo();
        demo.a.start();
        demo.b.start();
        demo.c.start();
    }

}
