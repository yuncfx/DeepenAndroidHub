package simpletest;

class NonGen {
    int num;

    NonGen(int i) {
        num = i;
    }

    int getNumber() {
        return num;
    }
}

/**
 * 有泛型的子类可以继承没有泛型的父类
 * 
 * @param <T>
 */
class Gen<T> extends NonGen {
    T ob;

    Gen(T o) {
        super(0);
        ob = o;
    }

    T getob() {
        return ob;
    }
}

/**
 * 每一个继承父类的子类，也必须继承父类的泛型
 *
 * @param <T>
 */
class Gen2<T> extends Gen<T> {
    Gen2(T o) {
        super(o);
    }
}

/**
 * 子类可以增加泛型
 * 
 * @param <T>
 * @param <V>
 */
class Gen3<T, V> extends Gen2<T> {
    T ob1;
    V ob2;

    Gen3(T o) {
        super(o);
    }

    Gen3(T o1, V o2) {
        super(o1);
        ob1 = o1;
        ob2 = o2;
    }

    /*
     * 重写方法，从这也能看出来，子类继承父类的泛型的必要性 (non-Javadoc)
     * 
     * @see month_09_generics.Gen#getob()
     */
    @Override
    T getob() {
        return ob1;
    };

    V getob2() {
        return ob2;
    }
}

public class TestGenericHierarchy {
    public static void main(String[] args) {
        Gen<Integer> iOb = new Gen<Integer>(88);
        Gen2<Integer> iOb2 = new Gen2<Integer>(99);
        Gen2<String> strOb2 = new Gen2<String>("Generics Test");

        if (iOb instanceof Gen<?>) {
            System.out.println("iOb is instance of Gen<?>");
        }

        if (iOb instanceof Gen) {
            System.out.println("iOb is instance of Gen");
        }

        /**
         * Cannot perform instanceof check against parameterized type Gen2
         * <Integer>. Use the form Gen2<?> instead since further generic type
         * information will be erased at runtime
         * 泛型在运行期间，会被擦除，因此编译失败，应该用通配符?代替具体的Integer
         */
        // if (iOb2 instanceof Gen2<Integer>) {
        // System.out.println("iOb2 is instance of Gen2<Integer>");
        // }

        Gen<Integer> intObject = new Gen<Integer>(88);
        Gen2<Long> longObject = new Gen2<Long>(99L);
        Gen2<Integer> long2 = new Gen2<Integer>(109);

        /**
         * Cannot cast from Gen<Integer> to Gen2<Long>
         */
        // longObject = (Gen2<Long>)intObject;

        /**
         * 泛型对象之间进行转换的条件必须同时满足以下两条： 两个类型互相兼容：Gen2 是Gen的子类 泛型类型相同：都是Integer
         */
        intObject = long2;
        /**
         * 这里是将Gen转换为Gen2，所以必须是强制类型转换
         */
        long2 = (Gen2<Integer>) intObject;
    }
}
