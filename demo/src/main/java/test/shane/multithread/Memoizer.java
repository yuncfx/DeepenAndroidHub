package test.shane.multithread;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author shane
 */

public class Memoizer<A, V> implements Computable<A, V> {

    private Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> mComputable;

    public Memoizer(Computable<A, V> computable) {
        this.mComputable = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true) {
            Future<V> future = cache.get(arg);
            if (future == null) {
                Callable<V> eval = () -> mComputable.compute(arg);
                FutureTask<V> ft = new FutureTask<>(eval);
                // get previous associated value, so this means this future was not added before!
                future = cache.putIfAbsent(arg, ft);
                if (future == null) {
                    future = ft;
                    ft.run();
                }
            }

            try {
                return future.get();
            } catch (CancellationException e) {
                cache.remove(arg, future);
            } catch (ExecutionException e) {
                throw launderThrowable(e);
            }
        }
    }

    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("Not unchecked", t);
        }
    }
}
