package simpletest;

import java.io.ObjectStreamException;

public class TestSingleton {

    public static void main(String[] args) {

        System.out.println(SingleTon1.getInstance());
        System.out.println(SingleTon2.getInstance());
        System.out.println(SingleTon2.getInstance2());
        System.out.println(SingleTon3.getInstance());
        System.out.println(SingletonEnum.INSTANCE);
        
    }
}

class SingleTon1 {
    private static final SingleTon1 st = new SingleTon1();

    private SingleTon1() {
    }

    public static SingleTon1 getInstance() {
        return st;
    }
    
    private Object readResolve() throws ObjectStreamException {
        return st;
    }
}

class SingleTon2 {
    private static SingleTon2 st;

    private SingleTon2() {
    }

    public static SingleTon2 getInstance() {
        if (null == st) {
            synchronized (SingleTon2.class) {
                if (null == st) {
                    st = new SingleTon2();
                }
            }

        }

        return st;
    }

    public static synchronized SingleTon2 getInstance2() {
        if (null == st) {
            st = new SingleTon2();
        }

        return st;
    }
    
    
    private Object readResolve() throws ObjectStreamException {
        return st;
    }

}

class SingleTon3 {
    
    private SingleTon3(){}
    public static SingleTon3 getInstance() {
        return SingleTon3Holder.sInstance;
    }
    
    private Object readResolve() throws ObjectStreamException {
        return SingleTon3Holder.sInstance;
    }
    
    static class SingleTon3Holder{
        private static final SingleTon3 sInstance = new SingleTon3();
    }
}

enum SingletonEnum {
    INSTANCE;
    public void doSomething() {
        System.out.println("do sth.");
    }
}