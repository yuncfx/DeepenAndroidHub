package concurrency.multithread;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author maohuawei created in 2018/10/8
 */
public final class ThreadPoolUtils {


    private static ThreadPoolUtils threadPoolUtils = new ThreadPoolUtils();

    public static ThreadPoolUtils getInstance() {
        return threadPoolUtils;
    }


    /**
     * 线程池
     * <p>
     * 常规方法
     * private static ExecutorService singleThreadExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
     * ----------
     * 线程优化
     * 获取CPU核心数
     * java Runtime.getRuntime().availableProcessors()
     * <p>
     * 核心线程数
     * CPU核心数+1
     * <p>
     * 最大线程数
     * CPU核心数*2+1
     */
    private static ExecutorService singleThreadExecutor;


    /**
     * 私有化构造方法
     */
    private ThreadPoolUtils() {
        singleThreadExecutor = new ThreadPoolExecutor(0, Runtime.getRuntime().availableProcessors() * 2 + 1,
                0L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100));
    }


    /**
     * 工作线程
     *
     * @param runnable
     */
    public void runSubThread(Runnable runnable) {
        if (singleThreadExecutor != null && !singleThreadExecutor.isShutdown()) {
            singleThreadExecutor.execute(runnable);
        } else {
            new Thread(runnable).start();
        }
    }

    /**
     * 关闭线程池
     */
    public void kill() {
        if (!singleThreadExecutor.isShutdown()) {
            singleThreadExecutor.shutdown();
        }
    }

    private static void t(long time, String print) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello world: " + print);
    }

    public static void main(String[] args) {
        ThreadPoolUtils utils = new ThreadPoolUtils();

        utils.runSubThread(() -> t(0, "111"));

        utils.runSubThread(() -> t(400, "222"));

        utils.runSubThread(() -> t(300, "333"));

        utils.runSubThread(() -> t(200, "444"));

        utils.runSubThread(() -> t(100, "555"));
    }
}
