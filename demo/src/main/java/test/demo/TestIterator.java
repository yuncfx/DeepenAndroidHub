package test.demo;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author shanemao
 */
public class TestIterator {

    private static final int DEFAULT_LIST_SIZE = 20;
    private List<String> mCurrentList = new ArrayList<>(DEFAULT_LIST_SIZE);
    private static final List<String> DEFAULT_LIST = new ArrayList<>(20);

    static {
        for (int i = 0; i < DEFAULT_LIST_SIZE; i++) {
            DEFAULT_LIST.add("i:" + i + ", value:" + i);
        }
    }

    {
        for (int i = 0; i < DEFAULT_LIST_SIZE; i++) {
            mCurrentList.add("i:" + i + ", value:" + i * Math.random() * 100);
        }
    }

    ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(10, 1200, 200, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "test iterator");
        }
    }, new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) {
        TestIterator testIterator = new TestIterator();
        testIterator.mThreadPoolExecutor.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 100; i++) {
            testIterator.addTasks();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void addTasks() {
        for (int i = 0; i < 100; i++) {
            mThreadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCurrentList = new ArrayList<>(DEFAULT_LIST);
                    System.out.println("re init");
                }
            });
        }

        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (String str : mCurrentList) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("str:" + str);
                }
            }
        });
    }
}
