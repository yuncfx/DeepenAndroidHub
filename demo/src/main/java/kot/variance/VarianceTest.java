package kot.variance;

import java.util.ArrayList;
import java.util.List;

public class VarianceTest {
    public static void main(String[] args) {
        List<Person> personArrayList = new ArrayList<>();
        personArrayList.add(new Person("aaa", 11));
        personArrayList.add(new Worker1("bbb", 12));
        personArrayList.add(new Worker2("ccc", 13));

        List<Worker1> worker1List = new ArrayList<>();
        worker1List.add(new Worker1("ddd", 14));
        List<Worker2> worker2List = new ArrayList<>();
        worker2List.add(new Worker2("eee", 15));

        setWork(personArrayList);

        // List<Worker1>和List<Worker2>不是List<Person>的子类
        // setWork(worker1List); // not compile
        // setWork(worker2List); // not compile
        setCovariantWork(worker1List);
        setCovariantWork(worker2List);

        // not compile, List<Person>显然不能传给List<Worker1>
        // setWorker2(personArrayList);
        setWorker2(worker1List);

        setContraVariantWork(personArrayList);
    }

    public static void setWork(List<Person> studentList) {
        for (Person o : studentList) {
            if (o != null) {
                o.toWork();
            }
        }
    }

    public static void setWorker2(List<Worker1> studentList) {
        for (Object o : studentList) {
            System.out.println("哈哈 " + o.toString());
        }
    }


    /**
     * 协变，只能读， 不能写, ? extends 对应 kotlin中的out，
     * 上界定为了 Person，类型写的是 ? ，编译器并不知道它的具体类型是什么，只要是继承自 Person 的类就可以，
     * 所以 get 出的对象肯定是 Person 的子类型，根据多态的特性，所以能够直接赋值给 Person ，但是 add 就不可以了，
     * 咱们可能添加的是 List<Worker1> 或 List<Worker2> ，还有可能是 List<Person>，所以编译器无法确定咱们添加的到底是什么类型
     * 就无法继续执行了，肯定就报错了
     */
    public static void setCovariantWork(List<? extends Person> studentList) {

        //  studentList.add(new Person("zhangsan", 2)); // compile error
        //  studentList.add(new Worker1("lisi", 23)); // compile error
        for (Person o : studentList) {
            if (o != null) {
                o.toWork();
            }
        }
    }

    /**
     * 逆变和协变一样，类型也是 ？，不过 ？ extends 是上界通配符，而 ？super 是下界通配符，
     * 它的范围包括 Worker1 和它的父类。和协变相反，逆变中是可以 add 的，因为 Worker1 一定是这个未知类型的子类型，
     * 所以是可以 add 的。这里也没有 get 的限制，会变成 Object ，因为在 Java 中所有类型都是 Object 的子类。
     *
     * ? super 对应 kotlin中的in
     */
    public static void setContraVariantWork(List<? super Worker1> studentList) {

        // Worker1一定是这个?的子类型， 所以能add
        studentList.add(new Worker1("zhangsan", 23));
        // 虽然Person是Worker1的父类，但是Worker1可能还有别的父类，?只知道是Worker1的父类，
        // 但不能确定类型，所以不能添加。
        // studentList.add(new Person("zhangsan", 23));

        if (!studentList.isEmpty()) {
            System.out.println(studentList.get(0));
        }
        for (Object o : studentList) {
            System.out.println("哈哈 " + o.toString());
        }
    }
}
