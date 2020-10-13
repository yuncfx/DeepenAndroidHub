package test.shane.puzzler.another;

/**
 * 你会觉得它应该打印 physics chemistry biology;毕竟 Java
 * 是在运行期对类进行装载的,所以它总是会访问到最新版本的类。但是更深入一
 * 点的分析会得出不同的结论。对于常量域的引用会在编译期被转化为它们所表示
 * 的常量的值[JLS 13.1]。这样的域从技术上讲,被称作常量变量(constant
 * variables),这可能在修辞上显得有点矛盾。一个常量变量的定义是:一个在
 * 编译期被常量表达式初始化的 final 的原始类型或 String 类型的变量[JLS
 * 4.12.4]。在知道了这些知识之后,我们有理由认为客户端程序会将初始值
 * Words.FIRST, Words.SECOND, Words.THIRD 编译进 class 文件,然后无论 Words
 * 类是否被改变,客户端都会打印 the null set。
 * 这种分析可能是有道理的,但是却是不对的。如果你运行了程序,你会发现它打
 * 印的是 the chemistry set。这看起来确实太奇怪的了。它为什么会做出这种事
 * 情呢?答案可以在编译期常量表达式(compile-time constant expression)[JLS
 * 15.28]的精确定义中找到。它的定义太长了,就不在这里写出来了,但是理解这
 * 个程序的行为的关键是 null 不是一个编译期常量表达式。
 * <p>
 * 由于常量域将会编译进客户端,API 的设计者在设计一个常量域之前应该深思熟
 * 虑。如果一个域表示的是一个真实的常量,例如 π 或者一周之内的天数,那么
 * 将这个域设为常量域没有任何坏处。但是如果你想让客户端程序感知并适应这个
 * 域的变化,那么就不能让这个域成为一个常量。有一个简单的方法可以做到这一
 * 点:如果你使用了一个非常量的表达式去初始化一个域,甚至是一个 final 域,
 * 那么这个域就不是一个常量。你可以通过将一个常量表达式传给一个方法使得它
 * 变成一个非常量,该方法将直接返回其输入参数。
 */
public class Words {
    private Words() {
    }

    public static final String FIRST = "the";
    /**
     * null 不是常量变量表达式，因此SECOND不是常量变量，不会在编译期直接被写入到class文件
     */
    public static final String SECOND = null;
    public static final String THIRD = "set";
}

/**
 * 现在假设你像下面这样改变了那个库类并且重编译了这个类,但并不重编译客户端的程序:
 */
class Words2 {
    private Words2() {
    }

    // 是常量变量
    public static final String FIRST = "physics";
    public static final String SECOND = "chemistry";
    public static final String THIRD = "biology";
}

