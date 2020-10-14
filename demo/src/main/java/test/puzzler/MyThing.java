package test.puzzler;

/**
 * @author shane
 */

class Thing {
    public Thing(int i) {

    }
}

class SomeOtherClass {
    public static int func() {
        return -1;
    }
}

/**
 * 交替构造器调用机制 （alternate constructor invocation）
 */
public class MyThing extends Thing {
    private final int arg;

    public MyThing() {
        //can't reference arg before supertype constructor has been called
//        super(arg = SomeOtherClass.func());
        this(SomeOtherClass.func());
    }

    private MyThing(int i) {
        super(i);
        arg = i;
    }
}
