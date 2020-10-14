package test.refactor;

// 线程范围内共享变量的概念与作用:

import java.util.HashMap;

import java.util.Map;

import java.util.Random;

public class ThreadScopeShareData {

    private static int data = 0;

    private static Map<Thread, Integer> threadData = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {

            new Thread(() -> {

                // 为什么static 的data变量放在线程内不会被重复赋值？
                int data = new Random().nextInt();
                System.out.println(Thread.currentThread().getName()
                        + " has put data of :" + data);
                threadData.put(Thread.currentThread(), data);
                new A().get();
                new B().get();

            }).start();

        }

    }

    static class A {

        public void get() {

            int data = threadData.get(Thread.currentThread());

            System.out.println("A from " + Thread.currentThread().getName()

                    + " get data of :" + data);

        }

    }

    static class B {

        public void get() {

            int data = threadData.get(Thread.currentThread());

            System.out.println("B from " + Thread.currentThread().getName()

                    + " get data of :" + data);

        }

    }

}