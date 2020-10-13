package test.temp.refactor;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shane
 *         用于实现两个人之间的数据交换，每个人在完成一定的事务后想与对方交换数据。
 *         第一个先拿出数据的人将一直等待第二个人拿着数据到来时，才能彼此交换数据。
 */

public class ExchangerTest {

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();

        final Exchanger<String> exchanger = new Exchanger<>();
        service.execute(() -> {

            try {

                String data1 = "zxx";

                System.out.println("线程" + Thread.currentThread().getName()
                        + "正在把数据" + data1 + "换出去");

                Thread.sleep((long) (new Random().nextInt(10000)));

                String data2 = exchanger.exchange(data1);

                System.out.println("线程" + Thread.currentThread().getName()

                        + "换回的数据为" + data2);

            } catch (Exception e) {

                e.printStackTrace();

            }

        });
        service.execute(() -> {

            try {
                String data1 = "lhm";

                System.out.println("线程" + Thread.currentThread().getName()
                        + "正在把数据" + data1 + "换出去");
                Thread.sleep((long) (new Random().nextInt(10000)));
                String data2 = exchanger.exchange(data1);
                System.out.println("线程" + Thread.currentThread().getName()
                        + "换回的数据为" + data2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        service.shutdown();
    }
}