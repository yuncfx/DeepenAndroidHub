package test.shanemao.puzzler;

/**
 * @author shane
 *         根据一个肤浅的分析会判断该程序不能通过编译。reproduce 方法中的匿名类试
 *         图调用 Twisted 类中的私有方法 name。一个类不能调用另一个类的私有方法,
 *         是吗?如果你试图编译这个程序,你会发现它可以成功地通过编译。在顶层的类
 *         型(top-level type)中,即本例中的 Twisted 类,所有的本地的、内部的、嵌
 *         套的和匿名的类都可以毫无限制地访问彼此的成员[JLS 6.6.1]。这是一个欢乐
 *         的大家庭。
 *         在了解了这些之后,你可能会希望程序打印出 reproduce,因为它在 new
 *         Twisted(“reproduce”)实例上调用了 printName 方法,这个实例将字符
 *         串”reproduce”传给其超类的构造器使其存储到它的 name 域中。printName 方
 *         法调用 name 方法,name 方法返回了 name 域的内容。但是如果你运行这个程序,
 *         你会发现它打印的是 main。现在的问题是它为什么会做出这样的事情呢?
 *         这种行为背后的原因是私有成员不会被继承[JLS 8.2]。在这个程序中,name 方
 *         法并没有被继承到 reproduce 方法中的匿名类中。所以,匿名类中对于 printName
 *         方法的调用必须关联到外围(“main”)实例而不是当前(“reproduce”)实例。这就是含有正确名称的方法的最小外围范围(enclosing scope)
 */
public class Twisted {
    private final String name;

    Twisted(String name) {
        this.name = name;
    }

    private String name() {
        return name;
    }

    private void reproduce() {
        new Twisted("reproduce") {
            void printName() {
                System.out.println(name());
            }
        }.printName();
    }

    public static void main(String[] args) {

        // main
        new Twisted("main").reproduce();
    }
}