package simpletest;

/**
 * 
 * @param <T>
 *            T 必须实现Comparable接口
 */
interface MinMax<T extends Comparable<T>> {
    T min();

    T max();
}

/**
 * 
 * 这里为什么T extends Comparable<T>放在前面， 而不是implements之后，
 * 因为泛型是前面定义，后面用；不能前面定义了一个T，后面使用的时候再去extends Comparable<T> 
 * 而且这里必须要extends Comparable<T>， 因为MinMax的接口定义就是使用了泛型<T extends Comparable<T>>
 * 
 * @param <T>
 */
class MyClass<T extends Comparable<T>> implements MinMax<T> {

    T[] vals;

    MyClass(T[] o) {
        vals = o;
    }

    @Override
    public T min() {
        T v = vals[0];

        for (int i = 1; i < vals.length; i++)
            if (vals[i].compareTo(v) < 0)
                v = vals[i];

        return v;
    }

    @Override
    public T max() {
        T v = vals[0];

        for (int i = 1; i < vals.length; i++)
            if (vals[i].compareTo(v) > 0)
                v = vals[i];

        return v;
    }

}

public class TestGenericInterface {
    public static void main(String[] args) {
        Integer inums[] = { 3, 6, 2, 8, 6 };
        Character chs[] = { 'A', 'r', 'V', 'w' };

        MyClass<Integer> iob = new MyClass<Integer>(inums);
        MyClass<Character> cob = new MyClass<Character>(chs);

        System.out.println("Max value in inums: " + iob.max());
        System.out.println("Min value in inums: " + iob.min());

        System.out.println("Max value in chs: " + cob.max());
        System.out.println("Min value in chs: " + cob.min());
    }
}
