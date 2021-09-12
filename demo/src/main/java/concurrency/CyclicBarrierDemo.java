package concurrency;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.concurrent.*;

import org.junit.Test;

/**
 * CyclicBarriers�ڹ̶��������߳��ڲ�ʱ����Ҫ����ȴ��ĳ���������á�
 * ����������ҫ����е���Ҷ�������ʱ����Ϸ�Ż���ʽ��ʼ��
 * 
 * �����ظ�ʹ�ã� CyclicBarrier�����������̶߳��ͷź� �ָֻ�����ʼ״̬��
 * 
 * {@link CyclicBarrier#CyclicBarrier(int parties, Runnable barrierAction)} //�̵߳���դ����������ִ��barrierAction
 * {@link CyclicBarrier#CyclicBarrier(int parties)} // parties ��������߳���������߳���С��parties�� �������̶߳���block�� ���򣬶�������̻߳�block��
 * {@link CyclicBarrier#await()} //�����̶߳�ִ�е�դ����ǰ����������ȴ�, ��������await, ���ظ�ʹ��barrier.
 * {@link CyclicBarrier#reset()} //reset barrier, դ���������߳��ͷź������¿���, ͨ�����ǲ�Ӧ���ֶ�����reset������
 * 
 * ԭ��ReentrantLock �� condition
 * 
 * @author Shane
 *
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {

		// preAction ����ִ��
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
