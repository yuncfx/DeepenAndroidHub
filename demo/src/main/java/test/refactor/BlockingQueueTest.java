package test.refactor;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * @author shane
 *         阻塞队列，查看文档，只有put()和take()方法才有阻塞功能
 */

public class BlockingQueueTest {

    public static void main(String[] args) {

        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);

        for (int i = 0; i < 2; i++) {

            new Thread(() -> {

                while (true) {
                    try {

                        Thread.sleep((long) (new Random().nextInt(1000)));

                        System.out.println(Thread.currentThread().getName()
                                + "准备放数据!");

                        queue.put(1);

                        System.out.println(Thread.currentThread().getName()
                                + "已经放了数据, " + "队列目前有" + queue.size()
                                + "个数据");
                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }


                }

            }).start();

        }

        new Thread(() -> {

            while (true) {

                try {

                    // 将此处的睡眠使劲啊分别改为100和1000，观察运行结果
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()
                            + "准备取数据! ");
                    queue.take();

                    System.out.println(Thread.currentThread().getName()
                            + "已经取走数据， " + "队列目前有" + queue.size() + "个数据");

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

            }

        }).start();

    }

}