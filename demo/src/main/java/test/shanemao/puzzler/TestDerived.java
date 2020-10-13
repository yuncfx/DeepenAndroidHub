package test.shanemao.puzzler;

class TestBase {
    public void f() {
    }

    public static void f1() {
    }
}

public class TestDerived extends TestBase {

    private int size;
    /**
     * Obscures type java.lang.System
     * 一个变量可以遮掩具有相同名字的一个类型， 只要它们都在同一个范围内： 如果这个名字被
     * 用于变量与类型都被许可的范围，那么它将引用到变量上。相似地，一个变量或一个类型可
     * 以遮掩一个包。 遮掩是唯一一种两个名字位于不同的名字空间的名字重用形式， 这些名字空
     * 间包括：变量、包、方法或类型。如果一个类型或一个包被遮掩了，那么你不能通过其简单
     * 名引用到它， 除非是在这样一个上下文环境中， 即语法只允许在其名字空间中出现一种名字。
     * 遵守命名习惯就可以极大地消除产生遮掩的可能性 [JLS 6.3.2, 6.5] ：
     */
    static String System;

    static String sentence = "I don't know.";

    public TestDerived() {
    }

    /**
     * 尽管遮蔽通常是被劝阻的，但是有一种通用的惯用法确实涉及遮蔽。构造器经常
     * 将来自其所在类的某个域名重用为一个参数，以传递这个命名域的值。这种惯用
     * 法并不是没有风险，但是大多数 Java 程序员都认为这种风格带来的实惠要超过
     * 其风险：
     */
    public TestDerived(int size) {
        this.size = size;
    }

    /**
     * 一个实例方法可以覆写 （ override ） 在其超类中可访问到的具有相同签名的所有实例方法 [JLS
     * 8.4.8.1] ，从而使能了动态分派（ dynamic dispatch ） ；换句话说， VM 将基于实例的运行期类
     * 型来选择要调用的覆写方法 [JLS 15.12.4.4] 。覆写是面向对象编程技术的基础，并且是唯一
     * 没有被普遍劝阻的名字重用形式：
     */
    @Override
    public void f() {
    } // overrides Base.f()

    /**
     * 一个域、 静态方法或成员类型可以分别隐藏 （ hide ） 在其超类中可访问到的具有相同名字 （对
     * 方法而言就是相同的方法签名）的所有域、静态方法或成员类型。隐藏一个成员将阻止其被
     * 继承
     */
    public static void f1() {
    }

    /**
     * 在某个类中的方法可以重载（ overload ）另一个方法，只要它们具有相同的名字和不同的签
     * 名。由调用所指定的重载方法是在编译期选定的 [JLS 8.4.9, 15.12.2] ：
     */
    public void f(int i) {
    } // int overloading

    public void f(String s) {
    } // String overloading

    /**
     * 一个变量、方法或类型可以分别遮蔽（ shadow ）在一个闭合的文本范围内的具有相同名字
     * 的所有变量、 方法或类型。 如果一个实体被遮蔽了， 那么你用它的简单名是无法引用到它的；
     * 根据实体的不同，有时你根本就无法引用到它 [JLS 6.3.1] ：
     */
    public static void main(String[] args) {
        // shadows static field
        String sentence = "I know!";
        // prints local variable

        // Next line won't compile: System refers to static field
        //System.out.println("hello, obscure world!");
        //System.out.println(sentence);
    }
}