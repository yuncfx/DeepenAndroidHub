package test.temp.refactor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shane
 *         空中网面试题1 ： 自己写的代码
 */

public class AirNetThreadTest {

    public static void main(String[] args) {
        System.out.println("begin : " + (System.currentTimeMillis() / 1000));

        /*
         * 模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。
         * 修改程序代码，开四个线程让这16个对象在4秒内打完。
         */
        ExecutorService service = Executors.newFixedThreadPool(4);

        Runnable r = null;
        // 这行代码不能改动
        for (int i = 0; i < 16; i++) {
            // 这行代码不能改动
            final String log = "" + (i + 1);
            if (i % 4 == 0) {
                r = new MyRunnable(log);
                System.out.println("r = " + r);
                service.execute(r);
            } else {
                if (r != null) {
                    service.execute(r);
                }
            }
        }

        service.shutdown();

    }


    /**
     * parseLog方法内部的代码不能改动
     */
    private static void parseLog(String log) {

        System.out.println(log + ":" + (System.currentTimeMillis() / 1000));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    static class MyRunnable implements Runnable {

        String log = null;

        MyRunnable(String log) {
            this.log = log;
        }

        @Override
        public void run() {
            AirNetThreadTest.parseLog(log);
        }

    }

}