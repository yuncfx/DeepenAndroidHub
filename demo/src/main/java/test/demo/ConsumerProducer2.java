package test.demo;

import java.util.ArrayList;
import java.util.List;

public class ConsumerProducer2 {

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Producer consumer = new Producer(buffer, 1);
        Consumer provider = new Consumer(buffer, 1);

        new Thread(consumer).start();
        new Thread(provider).start();
    }

    /**
     * 生产者
     */
    static class Producer extends Thread {
        private Buffer buffer;
        private int number;

        public Producer(Buffer b, int number) {
            buffer = b;
            this.number = number;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    // 模拟生产数据
                    sleep(500);
                } catch (InterruptedException e) {
                }

                // 将数据放入缓冲区
                buffer.put(i);
                System.out.println("生产者 #" + this.number + " put: " + i);
            }

            System.out.println("生产者 # finished");
        }
    }

    /**
     * 消费者
     */
    static class Consumer extends Thread {
        private Buffer buffer;
        private int number;

        public Consumer(Buffer b, int number) {
            buffer = b;
            this.number = number;
        }

        @Override
        public void run() {
            int value;
            for (int i = 0; i < 10; i++) {
                // 从缓冲区中获取数据
                value = buffer.get();
                try {
                    // 模拟消费数据
                    sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println("消费者 #" + this.number + " got: " + value);
            }

            System.out.println("消费者 # finished");
        }
    }

    /**
     * 缓冲区
     */
    static class Buffer {
        private List<Integer> data = new ArrayList<>();
        private static final int MAX = 10;
        private static final int MIN = 0;

        public synchronized int get() {
            while (MIN == data.size()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            Integer i = data.remove(0);
            notifyAll();
            return i;
        }

        public synchronized void put(int value) {
            System.out.println("put :" + data.size());
            while (MAX == data.size()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            data.add(value);
            notifyAll();
        }
    }
}
