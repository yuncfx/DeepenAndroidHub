package com.test.demo;

public class TestInstance {
    public TestInstance(int i) {
        if (i == 2) {
            throw new IllegalArgumentException("i == 2");
        }
    }
    private void printMsg() {
        System.out.println("hello world");
    }
    public static void main(String[] args) {
        TestInstance instance = null;
        try {
            instance =  new TestInstance(2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        System.out.println("instance:" + instance);
        if (instance != null) {
            instance.printMsg();
        }
    }
}
