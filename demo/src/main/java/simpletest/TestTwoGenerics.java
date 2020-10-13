package simpletest;

public class TestTwoGenerics {

    public static void main(String[] args) {
        TwoGen<Integer, String> twoGen = new TwoGen<>(2, "Generics");
        twoGen.showTypes();

        int t = twoGen.getFirst();
        System.out.println("t : " + t);

        String v = twoGen.getSecond();
        System.out.println("v : " + v);

        // 我们可以看到Super类上的泛型Father实际上并不是Father类，而本质上是一个形式参数
        Super<Father> s = new Super<>(new Father());
        // 此处可以传入Child对象来，来作为泛型，因为Child也可以上溯为Father，而不需要强制类型转换
        Super<Father> s2 = new Super<>(new Child());

        Super<Child> ch = new Super<>(new Child());
        /**
         * Type mismatch: cannot convert from Super<Child> to Super
         * <Father> Super<Child>不能转换为Super<Father>，它们不构成父子类关系
         */
        // s = ch;
        Super<String> s3 = new Super<>("hello");
    }
}

class TwoGen<T, V> {
    T t;
    V v;

    public TwoGen(T t, V v) {
        this.t = t;
        this.v = v;
    }

    public T getFirst() {
        return t;
    }

    public V getSecond() {
        return v;
    }

    void showTypes() {
        System.out.println("Type of T is " + t.getClass().getName());

        System.out.println("Type of V is " + v.getClass().getName());
    }
}

// 该Father其实是一个泛型， 而不是上面的Father类，本质上就是一个T或E
class Super<Father> {
    Super(Father f) {
        System.out.println(f);
    }
}

class ThreeGen<T, V, S> {

}