package simpletest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import collections.Person;

class GenericClazz<T> {
    private double val;

    private T t;

    /**
     * 在构造方法上，同样可以定义泛型； 注意，在方法上的T，即使和类上面的泛型T名字一样，还是两个不同的泛型。 方法上的T会覆盖类上的T
     * 
     * @param arg
     * 
     *            warning : The type parameter T is hiding the type T
     */
    public <T extends Number> GenericClazz(T arg) {
        val = arg.doubleValue();
    }

    /**
     * 尖括号里的多个泛型，名字不能一样，否则编译报错。
     */
    // public <E, E> void testTwoType() {
    //
    // }

    /**
     * 返回值是T， 传入的参数是一个是T的子类，一个是T的父类
     * 
     * @param coll
     * @param cmp
     * @return
     */
    public static <T> T max(Collection<? extends T> coll, Comparator<? super T> cmp) {
        T candidate = coll.iterator().next();
        for (T elt : coll) {
            if (cmp.compare(candidate, elt) < 0) {
                candidate = elt;
            }
        }
        return candidate;
    }

    /**
     * Cannot make a static reference to the non-static type T
     * 静态方法不能使用类上的泛型，要使用泛型，只能在方法上定义
     * 
     * @return
     */
    // public static T getT() {
    //
    // }
    
    /**
     * T作为一个Type，当然也可以用来作为数组，或者别个
     * @param t
     */
    public void set(T[] t) {
        
    }

    /**
     * extends Father 包含Father, super Child 包含Child
     * extends表示继承Father，注意接口在泛型中使用的也是extends super表示是Child的父类
     * 
     * @param father
     * @param child
     */
    public void testSuperExtends(Person<? extends Father> father, Person<? super Child> child) {
        System.out.println(father);
        System.out.println(child);
    }

    /**
     * 使用通配符?
     * 
     * @param list
     */
    public static void printList(List<?> list) {
        for (Object element : list) {
            System.out.println(element);
        }
    }

    void showValue() {
        System.out.println("val : " + val);
    }
}

/**
 * 也不能采用如下的方式定义类
 */
// class <T> TestG <T extends Number>{
//
// }
/**
 * 在类上，不能使用通配符'?'
 */
//class TestG2 <? extends Number> {}

public class TestGenericMethod {
    /**
     * 泛型类定义的泛型，在整个类中有效； 如果被方法使用, 那么泛型类的对象明确要操作的具体类型后，该方法要操作的类型就已经固定了。
     * 为了让不同方法操作不同类型，而且使用类型还不确定时，可以将泛型定义在方法上。
     * 
     * 定义方式： public [static] <T> boolean fun(T t){}
     * 
     * @param t
     * @param y
     * @return
     */

    static <T, V extends T> boolean isIn(T t, V[] y) {
        for (int i = 0; i < y.length; i++) {
            if (t.equals(y[i])) {
                return true;
            }
        }
        return false;
    }

    public <T> void isIn(T t, String str) {
        System.out.println(t + " : " + str);
    }

    public static void main(String[] args) {
        Integer nums[] = { 1, 2, 3, 4, 5 };

        if (isIn(2, nums)) {
            System.out.println("2 is in nums");
        }
        if (!isIn(7, nums)) {
            System.out.println("7 is not in nums");
        }

        // Use isIn() on Strings.
        String strs[] = { "one", "two", "three", "four", "five" };

        if (isIn("two", strs)) {
            System.out.println("two is in strs");
        }

        if (!isIn("seven", strs)) {
            System.out.println("seven is not in strs");
        }

        GenericClazz<Person<String>> test = new GenericClazz<Person<String>>(100);
        GenericClazz<Person<String>> test2 = new GenericClazz<Person<String>>(123.5F);

        test.showValue();
        test2.showValue();

        test.testSuperExtends(new Person<Father>(new Father()), new Person<Child>(new Child()));

        List<String> list1 = new ArrayList<String>();

        list1.add("Hello");
        list1.add("world");
        // 使用静态方法时，类的泛型不需要传入实际参数
        GenericClazz.printList(list1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(89);
        list2.add(90);
        // 使用静态方法时，类的泛型不需要传入实际参数
        GenericClazz.printList(list2);

    }
}
