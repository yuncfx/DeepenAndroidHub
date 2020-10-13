package test.temp.refactor;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author shane
 *         第三题 ： 当每个线程中指定的key相等时，这些相等的key的线程应每隔一秒依次输出时间值（要用互斥），
 *         <p>
 *         如果key不同，则并行执行（相互之间不互斥）。
 */
//不能改动此Test类
public class AirNetThreadTest3 extends Thread {

    private TestDo testDo;
    private String key;
    private String value;

    public AirNetThreadTest3(String key, String key2, String value) {

        this.testDo = TestDo.getInstance();


         /*
         * 常量"1"和"1"是同一个对象，下面这行代码就是要用"1" + ""的方式产生新的对象，以实现内容
         * 没有改变，仍然相等（都还为"1"）,但对象却不再是同一个的效果
         */
        this.key = key + key2;


        /*
        * 是同一个对象 a = "1" + ""; b = "1" + "";
        */

        this.value = value;


    }


    public static void main(String[] args) {

        AirNetThreadTest3 a = new AirNetThreadTest3("1", "", "1");
        AirNetThreadTest3 b = new AirNetThreadTest3("1", "", "2");
        AirNetThreadTest3 c = new AirNetThreadTest3("3", "", "3");
        AirNetThreadTest3 d = new AirNetThreadTest3("4", "", "4");

        System.out.println("begin:" + (System.currentTimeMillis() / 1000));

        a.start();
        b.start();
        c.start();
        d.start();

    }

    @Override
    public void run() {
        testDo.doSome(key, value);
    }


}


// 只能修改这个类

class TestDo {

    private TestDo() {

    }

    private final static TestDo INSTANCE = new TestDo();

    static TestDo getInstance() {
        return INSTANCE;
    }


    // 不能使用这个，

    // private ArrayList<Object> keys = new ArrayList<>();

    private CopyOnWriteArrayList<Object> keys = new CopyOnWriteArrayList<>();


    // 此处不能简单使用key作为synchronized的锁，因为两个key值相同，却不是同一个对象！

    void doSome(Object key, String value) {
        Object o = key;
        if (!keys.contains(o)) {
            keys.add(o);
        } else {

            for (Object key1 : keys) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (key1.equals(o)) {
                    o = key1;
                }

            }

        }

        synchronized (o)
        // 以大括号内的是需要局部同步的代码，不能改动！
        {

            try {
                Thread.sleep(1000);
                System.out.println(key + ":" + value + ":"

                        + (System.currentTimeMillis() / 1000));

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }

}
