package test.puzzler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author shane
 *         <p>
 *         使这个程序产生了预料之外的行为的根本原因就是 WorkerThread 类的作者使用
 *         了实例上的锁来确保 quit 方法和 keepWorking 方法的互斥，但是这种用法与超
 *         类（Thread）内部对该锁的用法发生了冲突。这里的教训是：除非有关于某个类
 *         的详细说明作为保证， 否则千万不要假设库中的这个类对它的实例或类上的锁会
 *         做（或者不会做）某些事情。对于库的任何调用都可能会产生对 wait、notify、
 *         notifyAll 方法或者某个同步化方法的调用。所有这些，都可能对应用级的代码
 *         产生影响。
 *         <p>
 *         如果你需要获得某个锁的完全控制权， 那么就要确定没有任何其他人能够访问到
 *         它。如果你的类扩展了库中的某个类，而这个库中的类可能使用了它的锁，或者
 *         如果某些不可信的人可能会获得对你的类的实例的访问权， 那么请不要使用与这
 *         个类或它的实例自动关联的那些锁。取而代之的，你应该在一个私有的域中创建
 *         一个单独的锁对象。
 *         <p>
 *         在 5.0 版本发布之前，用于这种锁对象的正确类型只有
 *         Object 或者它的某个普通的子类。从 5.0 版本开始，
 *         java.util.concurrent.locks 提供了 2 种可选方案：ReentrantLock 和
 *         ReentrantReadWriteLock。相对于 Object 类，这 2 个类提供了更好的机动性，
 *         但是它们使用起来也要更麻烦一点。它们不能被用在同步化的语句块
 *         （synchronized block）中，而且必须辅以 try-finally 语句对其进行显式的获
 *         取和释放。
 *         <p>
 *         另外一种可以修复这个程序的方法是让 Worker 类实现 Runnable 而不是扩展
 *         Thread，然后在创建每个工人线程的时候都使用 Thread(Runnable)构造器。这
 *         样可以将每个 Worker 实例上的锁与其线程上的锁进行解耦。这是一个规模稍大
 *         一些的重构（再一次证明了， 不要继承Thread， 而是实现Runnable接口为好）。
 */

public class Worker extends Thread {
    private volatile boolean quittingTime = false;

    private final Object lock = new Object();

    @Override
    public void run() {
        while (!quittingTime) {
            pretendToWork();
        }
        System.out.println("Beer is good");
    }

    private void pretendToWork() {
        try {
            // Sleeping on the job?
            Thread.sleep(300);
        } catch (InterruptedException ex) {
        }
    }

    /**
     * It's quitting time, wait for worker - Called by good boss
     */
    private synchronized void quit() throws InterruptedException {
        quittingTime = true;

        /*
         * Thread.join 方法在表示正在被连接（join）的那个 Thread 实例上调
         用 Object.wait() 方法。 这样就在等待期间释放了该对象上的锁。
         */
        join();
    }

    private void quit2() throws InterruptedException {
        synchronized (lock) {
            quittingTime = true;
            join();
        }
    }

    private void keepWorking2() {
        synchronized (lock) {
            quittingTime = false;
        }
    }

    /**
     * Rescind quitting time - Called by evil boss
     */
    private synchronized void keepWorking() {
        quittingTime = false;
    }

    /**
     * cannot stop
     */
    public static void main(String[] args)
            throws InterruptedException {
        final Worker worker = new Worker();
        worker.start();
        // Daemon thread
        Timer t = new Timer(true);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                worker.keepWorking();
            }
        }, 500);
        Thread.sleep(400);
        worker.quit();
    }
}