package concurrency;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.concurrent.*;

import org.junit.Test;

/**
 * CyclicBarriers在固定数量的线程在不时的需要互相等待的程序里，很有用。
 * 如在王者荣耀里，所有的玩家都就绪或超时后，游戏才会正式开始。
 * 
 * 可以重复使用， CyclicBarrier可以在所有线程都释放后， 又恢复到初始状态。
 * 
 * {@link CyclicBarrier#CyclicBarrier(int parties, Runnable barrierAction)} //线程到达栅栏处，优先执行barrierAction
 * {@link CyclicBarrier#CyclicBarrier(int parties)} // parties 必须等于线程数，如果线程数小于parties， 则所有线程都会block， 否则，多出来的线程会block。
 * {@link CyclicBarrier#await()} //所有线程都执行到栅栏处前，必须持续等待, 连续调用await, 可重复使用barrier.
 * {@link CyclicBarrier#reset()} //reset barrier, 栅栏在所有线程释放后，又重新可用, 通常我们不应该手动调用reset方法。
 * 
 * 原理：ReentrantLock 和 condition
 * 
 * @author Shane
 *
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {

		// preAction 优先执行
		CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {

			@Override
			public void run() {
				System.out.println("pre action ...");
			}

		});

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					barrier.await();
					System.out.println("runnable 1 ...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}

			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					int waitingNumber = barrier.getNumberWaiting();
					System.out.println(" reset waitingNumber = " + waitingNumber);
					barrier.await();
					System.out.println("runnable 2 ...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}

			}
		}).start();

		try {
			barrier.await();
			System.out.println("main-thread ...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		
		int waitingNumber = barrier.getNumberWaiting();
		System.out.println("waitingNumber = " + waitingNumber);		

	}
	
    HashMap map = new HashMap();
	
    //use for test case.
	@Test 
    public void concurrentTest() throws Exception { 
        int threads = 100; 
        final CyclicBarrier barrier = new CyclicBarrier(threads + 1); 
        for (int i = 0; i < threads; i++) { 
            new Thread() { 
                public void run() { 
                    try { 
                        barrier.await(); 
                        for (int i = 0; i < 10000; i++) 
                            map.put(i, i); 
                        for (int i = 0; i < 10000; i++) 
                            map.get(i); 
                        barrier.await(); 
                    } 
                    catch (Exception e) { 
                        e.printStackTrace(); 
                    } 
                } 
            }.start(); 
        } 
        barrier.await(); 
        barrier.await(); 
        assertEquals(10, map.size()); 
        System.out.println(map); 
    } 

}
