package test.refactor;


/**
 * @author shane
 *         多个线程访问共享共享数据的方式
 */
public class MultiThreadShareData {

    private static ShareData1 data1 = new ShareData1();

    public static void main(String[] args) {

        final ShareData1 data1 = new ShareData1();

        new Thread(() -> data1.decrement()).start();

        new Thread(() -> data1.increment()).start();

        new Thread(new MyRunnable1(data1)).start();

        new Thread(new MyRunnable2(data1)).start();

    }

}

class MyRunnable1 implements Runnable {

    private ShareData1 data1;

    MyRunnable1(ShareData1 data1) {
        this.data1 = data1;
    }

    @Override
    public void run() {
        data1.decrement();
    }

}

class MyRunnable2 implements Runnable {

    private ShareData1 data1;

    MyRunnable2(ShareData1 data1) {
        this.data1 = data1;
    }

    @Override
    public void run() {
        data1.increment();
    }

}

class ShareData1 {

    private int j = 0;

    synchronized void increment() {
        j++;
    }

    synchronized void decrement() {
        j--;
    }

}