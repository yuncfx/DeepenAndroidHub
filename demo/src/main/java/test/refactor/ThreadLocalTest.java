package test.refactor;

import java.util.Random;

/**
 * 使用ThreadLocal 维护线程内的变量， ThreadDeathEvent,  ThreadDeathRequest
 */
public class ThreadLocalTest {

    /**
     * ThreadLocal 存入的数据， 保持了数据在Thread内的独立性，每个线程取出各线程自己对应的数据
     * 多个数据可以存进一个实体类中
     */
    static ThreadLocal<Integer> x = new ThreadLocal<>();

    static ThreadLocal<MyThreadScopeData> myThreadScopeData = new ThreadLocal<>();

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {

            new Thread(() -> {

                // 为什么static 的data变量放在线程内不会被重复赋值？
                int data = new Random().nextInt();
                System.out.println(Thread.currentThread().getName()
                        + " has put data of :" + data);
                x.set(data);


                MyThreadScopeData myData = new MyThreadScopeData();
                myData.setAge(data);
                myData.setName("name" + data);
                myThreadScopeData.set(myData);

                MyThreadScopeData.getThreadInstance().setName("name" + data);
                MyThreadScopeData.getThreadInstance().setAge(data);

                new A().get();

                new B().get();

            }).start();

        }

    }

    static class A {

        public void get() {
            int data = x.get();
            System.out.println("A from " + Thread.currentThread().getName()

                    + " get data of :" + data);

            // MyThreadScopeData myData = myThreadScopeData.get();

            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();

            System.out.println("A from " + Thread.currentThread().getName()

                    + " getMyData :" + myData.getAge() + ", "

                    + myData.getName());

        }

    }

    static class B {

        public void get() {

            int data = x.get();

            System.out.println("B from " + Thread.currentThread().getName()

                    + " get data of :" + data);

            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();

            System.out.println("B from " + Thread.currentThread().getName()

                    + " getMyData :" + myData.getAge() + ", "

                    + myData.getName());
        }

    }

}
// 获取线程死亡之前的状态。

class MyThreadScopeData {

    private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<>();

    // 获取与线程有关的实例
    static/* synchronized */MyThreadScopeData getThreadInstance() {

        MyThreadScopeData instance = map.get();

        if (null == instance) {
            instance = new MyThreadScopeData();
            map.set(instance);
        }

        return instance;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}