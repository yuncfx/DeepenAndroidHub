package concurrency;

import java.util.concurrent.*;

/**
 * 
 * @author Shane
 * {@link Semaphore#tryAcquire()} # return immediately, no block, acquire a permit if success.
 * {@link Semaphore#acquire(int permits)} # return immediately if success, else blocked
 * {@link Semaphore#acquire()} # return immediately if success, else blocked
 * {@link Semaphore#release()} # release a permit
 * {@link Semaphore#release(int permits)} # release permits
 * 
 * semaphore一般用于需要限制资源的使用的场景。
 *
 */

public class SemaphoreDemo {
	public static void main(String[] args) {

		Semaphore semaphore = new Semaphore(5);
		// fair semaphore, not efficient as non fair semaphore
		Semaphore fairSemaphore = new Semaphore(5, true);

		new Thread(() -> {
			try {
				semaphore.acquire(2);
				System.out.println("acquire success 111");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaphore.release(2);
			}
		}).start();

		new Thread(() -> {
			try {
				semaphore.acquire(1);
				System.out.println("acquire success 222");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		semaphore.tryAcquire();
		System.out.println("acquire success in main-thread 333");
		
		
		semaphore.acquireUninterruptibly(2);
		System.out.println("acquire success in main-thread 444");

	}
}
