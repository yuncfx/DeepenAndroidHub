package test.temp.refactor;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author shane
 *         Semaphore 可以维护当前访问自身的线程个数，并提供了同步机制。使用Semaphore可以控制同时访问资源的线程个数，
 *         例如，实现一个文件允许的并发访问数。
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {

            Runnable runnable = () -> {

                try {
                    semaphore.acquire();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                System.out.println("线程" + Thread.currentThread().getName()
                        + "进入，当前已有" + (3 - semaphore.availablePermits())
                        + "个并发");

                try {
                    Thread.sleep((long) (new Random().nextInt(1000)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
                semaphore.release();

                // 下面代码有时候执行不准确，因为没有和上面的代码合成原子操作
                System.out.println("线程" + Thread.currentThread().getName()
                        + "已离开，当前已有" + (3 - semaphore.availablePermits())
                        + "个并发");

            };

            service.execute(runnable);

        }

        service.shutdown();

    }

}
