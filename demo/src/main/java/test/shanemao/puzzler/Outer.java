package test.shanemao.puzzler;

import java.lang.reflect.Constructor;

/**
 * @author shane
 */
public class Outer {
    public static void main(String[] args) throws Exception {
        //InstantiationException: Outer$Inner
        new Outer().greetWorld();
    }

    /**
     * 从 5.0 版本开始， 关于 Class.newInstance 的文档叙
     * 述道：如果那个 Class 对象“代表了一个抽象类（abstract class），一个接口
     * （interface），一个数组类（array class），一个原始类型（primitive type），
     * 或者是空（void）；或者这个类没有任何空的[也就是无参数的]构造器；或者实
     * 例化由于某些其他原因而失败，那么它就会抛出异常”[JAVA-API]。
     */


    private void greetWorld() throws Exception {
        System.out.println(Inner.class.newInstance());
    }

    private void greetWorldTwo() throws Exception {
        // Outer.class 表示该构造方法的参数类型是Outer
        Constructor c = Inner.class.getConstructor(Outer.class);
        System.out.println(c.newInstance(Outer.this));
    }

    /**
     * 一个非静态的嵌套类的构造器， 在编译的时候会将一个隐藏的参数作为它的第一
     * 个参数，这个参数表示了它的直接外围实例（immediately enclosing instance）
     * [JLS 13.1]。当你在代码中任何可以让编译器找到合适的外围实例的地方去调用
     * 构造器的时候，这个参数就会被隐式地传递进去。但是，上述的过程只适用于普
     * 通的构造器调用，也就是不使用反射的情况。当你使用反射调用构造器时，这个
     * 隐藏的参数就需要被显式地传递， 这对于 Class.newInstance 方法是不可能做到
     * 的。要传递这个隐藏参数的唯一办法就是使用
     * java.lang.reflect.Constructor。
     */
    public class Inner {
        @Override
        public String toString() {
            return "Hello world";
        }
    }

    /**
     * Inner 实例并不需要一个外围的 Outer 实例，
     * 所以可以将 Inner 类型声明为静态的（static）。除非你确实是需要一个外围实
     * 例，否则你应该优先使用静态成员类（static member class）而不是非静态成
     * 员类[EJ Item 18]
     * <p>
     * Java 程序的反射模型和它的语言模型是不同的。反射操作处于虚拟机层次，暴
     * 露了很多从 Java 程序到 class 文件的翻译细节。这些细节当中的一部分由 Java
     * 的语言规范来管理，但是其余的部分可能会随着不同的具体实现而有所不同。在
     * Java 语言的早期版本中，从 Java 程序到 class 文件的映射是很直接的，但是随
     * 着一些不能被虚拟机直接支持的高级语言特性的加入，如嵌套类（nested
     * class）、协变返回类型（covariant return types）、泛型（generics）和枚
     * 举类型（enums），使得这种映射变得越来越复杂了。
     * 考虑到从 Java 程序到 class 文件的映射的复杂度，请避免使用反射来实例化内
     * 部类。更一般地讲，当我们在用高级语言特性定义的程序元素之上使用反射的时
     * 候，一定要小心，从反射的视角观察程序可能不同与从代码的视角去观察它。
     */
    public static class InnerTwo {

    }

    class Inner1 extends Outer {

    }

    class Inner2 extends Inner1 {

    }
}