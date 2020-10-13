package test.temp.refactor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shane
 *         犹如倒计时计数器，调用CountDownLatch对象的countDown方法就将计数器减1，
 *         当计数到达0时，则所有等待者或者单个等待者开始执行。
 */

public class CountDownLatchTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {

            Runnable runnable = () -> {

                try {

                    System.out.println("线程"

                            + Thread.currentThread().getName() + "正准备接受命令");


                    cdOrder.await();

                    System.out.println("线程"

                            + Thread.currentThread().getName() + "已接受命令");

                    Thread.sleep((long) (Math.random() * 10000));

                    System.out.println("线程"
                            + Thread.currentThread().getName()

                            + "回应命令处理结果 ");


                } catch (Exception e) {

                    e.printStackTrace();

                } finally {
                    cdAnswer.countDown();
                }

            };

            service.execute(runnable);

        }

        try {

            Thread.sleep((long) (new Random().nextInt(10000)));

            System.out.println("线程" + Thread.currentThread().getName()

                    + "即将发布命令");

            cdOrder.countDown();

            System.out.println("线程" + Thread.currentThread().getName()

                    + "已发送命令，正在等待结果");
            cdAnswer.await();

            System.out.println("线程" + Thread.currentThread().getName()
                    + "已收到所有响应结果");

        } catch (Exception e) {

            e.printStackTrace();

        }

        service.shutdown();

    }

}
