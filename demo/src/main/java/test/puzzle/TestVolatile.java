package test.puzzle;

import java.util.concurrent.TimeUnit;

class MyData {
    public int mData;

    public void addTo60() {
        mData = 60;
    }
}

/**
 * @author hp
 */
public class TestVolatile {
    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println("hello sub thread");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println("sub thread: mData=" + myData.mData);
        }).start();

        while (myData.mData == 0) {
//            System.out.println("main thread in loop");
        }
        System.out.println("****** exit main thread: mData=" + myData.mData);
    }
}