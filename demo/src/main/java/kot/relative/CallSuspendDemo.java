package kot.relative;

import com.ssy.java2kot.Listener;
import com.ssy.java2kot.SuspendDemoKt;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

/**
 * @author shane
 */
public class CallSuspendDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture =
                SuspendDemoKt.doSomethingAsync();

        System.out.println("begin ..." + System.currentTimeMillis());

        String str = stringCompletableFuture.get();
        System.out.println(str + ", " + + System.currentTimeMillis());

        // not worked
        Object o = SuspendDemoKt.sayHello(new Continuation<String>() {
            @NotNull
            @Override
            public CoroutineContext getContext() {
                return EmptyCoroutineContext.INSTANCE;
            }

            @Override
            public void resumeWith(@NotNull Object o) {
                System.out.println("resumeWith: " + o);
            }
        });

        SuspendDemoKt.t(System.out::println);
    }
}
