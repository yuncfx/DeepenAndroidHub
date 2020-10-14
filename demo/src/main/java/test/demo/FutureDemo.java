package test.demo;

import org.junit.Test;

import java.util.concurrent.*;

public class FutureDemo {

    @Test
    public void test() {

        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() {
                System.out.println("FutureTask:"+Thread.currentThread().getName());
                return 123;
            }
        });

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                System.out.println("callable:"+Thread.currentThread().getName());
                return 456;
            }
        };

        /**
         * Future 不能直接被service submit
         */
        Future<Integer> future = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("future:"+Thread.currentThread().getName());
                return 789;
            }
        });

        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(task);

        Future<Integer> submit = service.submit(callable);
        try {
            Integer integer = task.get();
            System.out.println(integer);
            Integer integer1 = submit.get();
            System.out.println(integer1);
//            Integer integer2 = future.get();
//            System.out.println(integer2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
