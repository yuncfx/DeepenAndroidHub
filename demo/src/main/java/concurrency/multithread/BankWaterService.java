package concurrency.multithread;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shane
 */

public class BankWaterService implements Runnable {
    /**
     * ����4�����ϣ�������֮��ִ�е�ǰ���run����
     */
    private CyclicBarrier c = new CyclicBarrier(4, this);
    /**
     * ����ֻ��4��sheet������ֻ����4���߳�
     */
    private ExecutorService executor = Executors.newFixedThreadPool(4);
    /**
     * ����ÿ��sheet��������������
     */
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new
            ConcurrentHashMap<>();

    private void count() {
        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                // ���㵱ǰsheet���������ݣ��������ʡ��
                sheetBankWaterCount
                        .put(Thread.currentThread().getName(), 1);
                // ����������ɣ�����һ������
                try {
                    c.await();
                } catch (InterruptedException |
                        BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void run() {
        int result = 0;
        // ����ÿ��sheet������Ľ��
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }
        // ��������
        sheetBankWaterCount.put("result", result);
        System.out.println(result);
        executor.shutdown();
    }

    public static void main(String[] args) {
        BankWaterService bankWaterCount = new BankWaterService();
        bankWaterCount.count();
    }
}
