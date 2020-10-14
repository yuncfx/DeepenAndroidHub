package test.puzzler;

import java.util.Arrays;
import java.util.List;

/**
 * @author shane
 *         一个原生类型就是一个没有任何类型参数的泛型类或泛型接口的名字。例如,List<E>
 *         是一个泛型接口,List<String> 是一个参数化的类型,而 List 就是一个原生类型
 */
public class Pair<T> {
    private final T first;
    private final T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return first;
    }

    public T second() {
        return second;
    }

    public List<String> stringList() {
        return Arrays.asList(String.valueOf(first),
                String.valueOf(second));
    }

    public static void main(String[] args) {
        /*
        一个原生类型很像其对应的参数化类型,但是它的所有实例成员都要被替换掉,
        而替换物就是这些实例成员被擦除掉对应部分之后剩下的东西。具体地说,在一
        个实例方法声明中出现的每个参数化的类型都要被其对应的原生部分所取代
        [JLS 4.8]。我们程序中的变量 p 是属于原生类型 Pair 的,所以它的所有实例方
        法都要执行这种擦除。这也包括声明返回 List<String>的方法 stringList。编
        译器会将这个方法解释为返回原生类型 List。
        当 List<String>实现了参数化类型 Iterable<String>时,List 也实现了原生类
        型 Iterable。Iterable<String>有一个 iterator 方法返回参数化类型
        Iterator<String>,相应地,Iterable 也有一个 iterator 方法返回原生类型
        Iterator。当 Iterator<String>的 next 方法返回 String 时,Iterator 的 next
        方法返回 Object。因此,循环迭代 p.stringList()需要一个 Object 类型的循环
        变量,这就解释了编译器的那个奇怪的错误消息的由来。这种现象令人想不通的
        原因在于参数化类型 List<String>虽然是方法 stringList 的返回类型,但它与
        Pair 的类型参数没有关系
         */
        Pair p = new Pair<Object>(23, "skidoo");
        System.out.println(p.first() + " " + p.second());
        // not compile
//        for (String s : p.stringList()) {
//            System.out.print(s + " ");
//        }
    }
}