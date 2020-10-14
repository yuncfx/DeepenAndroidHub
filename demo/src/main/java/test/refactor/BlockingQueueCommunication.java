package test.refactor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * @author shane
 *         用两个具有1个空间的队列来实现同步通知的功能
 *         阻塞队列域Semaphore有些相似，但也不同。 阻塞队列是一方存放数据，另一方释放数据； Semaphore通常则是由同一方设置和释放信号量。
 */

public class BlockingQueueCommunication {

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
        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<>(1);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<>(1);

        {

            try {
                queue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        public void sub(int i) {
            try {
                queue1.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int j = 1; j <= 10; j++) {
                System.out.println("sub thread sequence of " + j + ", loop of " + i);
            }

            try {
                queue2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public void main(int i) {
            try {
                queue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int j = 1; j <= 100; j++) {
                System.out.println("main thread sequence of " + j + ", loop of " + i);
            }

            try {
                queue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
