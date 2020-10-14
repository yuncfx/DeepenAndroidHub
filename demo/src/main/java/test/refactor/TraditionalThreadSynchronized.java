package test.refactor;

/**
 * @author shane
 */
public class TraditionalThreadSynchronized {

    public static void main(String[] args) {
        new TraditionalThreadSynchronized().init();
    }

    private void init() {
        final Outputer outputer = new Outputer();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outputer.output("zhangxiaoxiang");
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outputer.output2("lihuoming");
            }

        }).start();

    }

}

class Outputer {

    public void output(String name) {
        int len = name.length();

        synchronized (this) {
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }

            System.out.println();
        }

    }

    public synchronized void output2(String name) {

        int len = name.length();
        for (int i = 0; i < len; i++) {
            System.out.print(name.charAt(i));
        }

        System.out.println();
    }

}