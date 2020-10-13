package test.shane.multithread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shane
 */

public class ExchangerTest {
    private static final Exchanger<String> exgr = new Exchanger<String>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(() -> {
            try {
                // A录入银行流水数据
                String A = "bank water A";
                exgr.exchange(A);
            } catch (InterruptedException e) {
            }
        });
        threadPool.execute(() -> {
            try {
                // B录入银行流水数据
                String B = "bank water A";
                String A = exgr.exchange(B);
                System.out.println("A equals B = " + A.equals(B) + ", A = "
                        + A + ", B = " + B);
            } catch (InterruptedException e) {
            }
        });
        threadPool.shutdown();
    }
}