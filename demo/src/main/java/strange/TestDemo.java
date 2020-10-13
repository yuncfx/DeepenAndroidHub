package strange;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TestDemo {

    @Test
    public void TestConcurrentException() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        final Random random = new Random();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("add item in thread");
                for (int i = 0; i < 20 ; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    list.add("" + random.nextInt(100));
                }
            }
        }).start();

//        for (String s: list) {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(s);
//        }

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String next = iterator.next();
            System.out.println(next);
        }
    }

    @Test
    public void testLock() {
        final Object lock = new Object();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (lock) {
                        System.out.println("111 thread");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("111 sub thread");
                            }
                        }).start();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (lock) {
                        System.out.println("222 thread");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("222 sub thread");
                            }
                        }).start();
                    }
                }
            }).start();
        }

    }
}
