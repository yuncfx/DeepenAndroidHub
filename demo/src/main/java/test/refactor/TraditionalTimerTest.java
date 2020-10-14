package test.refactor;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author shane
 *         quartz 三方定时器工具
 *         <p>
 *         每周周一至周五凌晨三点， 定时收邮件，周六周日休息。
 */
public class TraditionalTimerTest {
    public static void main(String[] args) {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("bombing!");
            }
        }, 10000, 3000);

        class MyTimerTask extends TimerTask {
            @Override
            public void run() {
                System.out.println("bombing!");
                new Timer().schedule(new MyTimerTask(), 2000);
            }
        }

        new Timer().schedule(new MyTimerTask(), 2000);
        while (true) {
            System.out.println(Calendar.getInstance().get(Calendar.SECOND));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}