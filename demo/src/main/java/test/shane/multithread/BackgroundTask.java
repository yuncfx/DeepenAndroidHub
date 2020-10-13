package test.shane.multithread;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class BackgroundTask<V> implements Runnable, Future<V> {
    private final FutureTask<V> computation = new Computation();

    private class Computation extends FutureTask<V> {
        public Computation() {
            super(BackgroundTask.this::compute);
        }

        @Override
        protected final void done() {
            GuiExecutor.instance().execute(() -> {
                V value = null;
                Throwable thrown = null;
                boolean cancelled = false;
                try {
                    value = get();
                } catch (ExecutionException e) {
                    thrown = e.getCause();
                } catch (CancellationException e) {
                    cancelled = true;
                } catch (InterruptedException ignored) {
                } finally {
                    onCompletion(value, thrown, cancelled);
                }
            });
        }
    }

    protected void setProgress(final int current, final int max) {
        GuiExecutor.instance().execute((Runnable) () -> onProgress(current, max));
    }

    /**
     * Called in the background thread
     */
    protected abstract V compute() throws Exception;

    /**
     * Called in the event thread
     */
    protected void onCompletion(V result, Throwable exception,
                                boolean cancelled) {
    }

    protected void onProgress(int current, int max) {
    }


    /**
     * Other Future methods just forwarded to computation
     */
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return computation.cancel(mayInterruptIfRunning);
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return computation.get();
    }

    @Override
    public V get(long timeout, TimeUnit unit)
            throws InterruptedException,
            ExecutionException,
            TimeoutException {
        return computation.get(timeout, unit);
    }

    @Override
    public boolean isCancelled() {
        return computation.isCancelled();
    }

    @Override
    public boolean isDone() {
        return computation.isDone();
    }

    @Override
    public void run() {
        computation.run();
    }
}