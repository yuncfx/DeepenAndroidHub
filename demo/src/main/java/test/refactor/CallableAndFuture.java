package test.refactor;


import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author shane
 */
public class CallableAndFuture {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        // Future 取得的结果类型和Callable返回的结果类型必须一致，这个是通过泛型来实现的。
        Future<String> future =
                // Callable要采用ExecutorService的submit方法提交，返回的future对象可以取消任务
                threadPool.submit(() -> {

                    Thread.sleep(2000);
                    return "hello";

                });


        System.out.println("waiting for result!");

        try {

            System.out.println("result : " + future.get(1, TimeUnit.SECONDS));
            System.out.println("result : " + future.get());

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);

        // CompletionService用于提交一组Callable任务，其take方法返回已完成的一个Callable任务对应的Future对象
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(threadPool2);


        for (int i = 0; i < 10; i++) {

            final int seq = i;

            completionService.submit(() -> {
                Thread.sleep(new Random().nextInt(5000));
                return seq;
            });

        }

        for (int i = 0; i < 10; i++) {

            try {
                System.out.println(completionService.take().get());

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }

    }

}