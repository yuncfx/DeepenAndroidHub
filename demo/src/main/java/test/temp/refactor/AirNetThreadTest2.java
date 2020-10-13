package test.temp.refactor;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shane
 *         // 空中网面试题2
 *         生产消费模式，
 *         请将程序修改成有10个线程来消费生产者产生的数据， 程序应保证每个被消费的产品都是按顺序被消费的，但是不规定消费者的顺序
 */

public class AirNetThreadTest2 {

    public static void main(String[] args) {

        System.out.println("begin : " + (System.currentTimeMillis()));
        //final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);

        // 旗语 ：
        //final Semaphore semaphore = new Semaphore(1);

        final Lock lock = new ReentrantLock();

        final SynchronousQueue<String> queue = new SynchronousQueue<>();


        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // 获得旗语
                    //semaphore.acquire();
                    // 锁住，不让别人take

                    lock.lock(); // 这一行代码是我自己写的。
                    String input = queue.take();
                    String output = TestDo.doSome(input);
                    System.out.println(Thread.currentThread().getName() + ":" + output);

                    // 释放旗语

                    //semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁，别人可以take了
                    lock.unlock(); // 这一行代码是我自己写的, 如果不用lock， 用semaphore就可以

                }

            }).start();

        }

        // 这行不能改动
        for (int i = 0; i < 10; i++) {
            // 这行不能改动
            String input = i + "";

            try {
                queue.put(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //String output = TestDo.doSome(input);
            //System.out.print(Thread.currentThread().getName() + ":" + output);

        }

    }

    /**
     * 不能改动此TestDo类
     */
    static class TestDo {

        public static String doSome(String input) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String output = input + ":" + (System.currentTimeMillis() / 1000);

            return output;

        }

    }

}
