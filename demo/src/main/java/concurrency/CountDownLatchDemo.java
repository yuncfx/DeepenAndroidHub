package concurrency;

import java.util.concurrent.*;

/**
 * 
 * @author Shane
 * 
 *         CountDownLatch是一个同步工具类，它允许一个或多个线程一直等待，直到其他线程的操作执行完后再执行，角色分为等待线程和被等待线程。
 *         一次性使用。
 *         
 *         {@link CountDownLatch#getCount()} // return value changes when countDown was called 
 *         {@link CountDownLatch#await}  // wait to be scheduled until the count is set to zero or interrupted
 *         {@link CountDownLatch#await(long timeout, TimeUnit unit)} wait to be scheduled until the count is set to zero or timeout or interrupted!
 *         {@link CountDownLatch#countDown()} // decrease the count down.
 *         {@link CountDownLatch#CountDownLatch(int count)} // set the count in construction 
 *
 */
public class CountDownLatchDemo {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(5);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("await...");
					latch.await();
					System.out.println("haha, finally countDown to zero. ");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		System.out.println("latch.getCount = " + latch.getCount());
		long count = latch.getCount();
		for (int i = 0; i < count; i++) {
			System.out.println("sleep and countDown !");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				latch.countDown();
			}
		}

	}

}
