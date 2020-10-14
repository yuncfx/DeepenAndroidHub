package test.refactor;

/**
 * @author shane
 *         子线程打印10次，主线程打印100次，反复交替50次：
 */
public class TraditionalThreadCommunication {

    public static void main(String[] args) {

        final Business business = new Business();

        new Thread(() -> {
            for (int i = 1; i <= 50; i++) {
                business.sub(i);
            }
        }).start();

        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }

    }

}


class Business {

    /**
     * 用到共同数据的方法，放到同一个类中
     */
    private boolean bShouldSub = true;

    synchronized void sub(int i) {

        while (!bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        for (int j = 1; j <= 10; j++) {
            System.out.println("sub thread sequence of " + j + ", loop of " + i);
        }

        bShouldSub = false;
        //notifyAll();
        this.notify();

    }


    public synchronized void main(int i) {
        while (bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 100; j++) {
            System.out.println("main thread sequence of " + j + ", loop of "
                    + i);
        }
        bShouldSub = true;
        //notifyAll();
        this.notify();
    }
}