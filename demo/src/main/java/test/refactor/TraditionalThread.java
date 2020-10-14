package test.refactor;

/**
 * 问题：如果在Thread子类覆盖的run方法中编写了运行代码，也为Thread子类对象传递了一个Runnable对象，
 * 那么，线程运行时的执行代码是子类的run方法的代码 还是 Runnable对象的run方法的代码？
 * 涉及到的一个以往的知识点：匿名内部类对象的构造方法如何调用父类的非默认构造方法。
 * 使用子类对象的run方法中的运行代码。
 * <p>
 * //子类初始化时一定要调用父类的构造方法，不调用行吗？
 * 因为子类继承了父类，那么就默认的含有父类的公共成员方法和公共成员变量，这些方法和变量在子类里不再重复声明。如果你初始化子类的时候，
 * 不初始化父类，那么你通过子类调用父类方法或变量的时候会出现什么情况呢？当然就是抛异常阿！所以，Java虚拟机会在你初始化子类的时候默认的初始化子类的父类。而且是一层一层的往上递进
 * 如果父类的构造方法是带参数的，而且没有无参数的构造方法，那么在子类的构造方法中必须显式地调用父类的构造方法。
 * 如果父类的构造方法是无参数的，那么在子类中写不写都可以，不写的话会隐式地调用。
 */
public class TraditionalThread {

    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("1:" + Thread.currentThread().getName());
                    System.out.println("2:" + this.getName());
                }
            }
        };
        t.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("1:" + Thread.currentThread().getName());
                }
            }

        });

        thread2.start();
    }
}