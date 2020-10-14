package test.refactor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 *
 * @author shane
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        // 创建固定大小的线程池
        // ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // 创建缓存线程池
        // ExecutorService threadPool = Executors.newCachedThreadPool();

        //如何实现线程死了之后重新启动？ 用单一线程池， 保证一个线程存在，旧线程死后，新线程替补上来
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {

            final int task = i;

            threadPool.execute(() -> {

                for (int j = 0; j < 10; j++) {

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()
                            + " loop of " + j + " for task of " + task);

                }

            });

        }

        System.out.println("all of 10 tasks have committed !");
        threadPool.shutdown();
        Executors.newScheduledThreadPool(3).schedule(() -> System.out.println("bombing!"), 6, TimeUnit.SECONDS);

        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(() -> System.out.println("bombing...!"), 6, 2, TimeUnit.SECONDS);

    }

}
