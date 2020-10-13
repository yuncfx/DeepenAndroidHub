package test.shanemao.puzzler;

/**
 * @author : shane
 *         <p>
 *         实例初始化操作是先于构造器的程序体而运行的。实例初始化操作抛出的
 *         任何异常都会传播给构造器。如果初始化操作抛出的是被检查异常,那么构造器
 *         必须声明也会抛出这些异常,但是应该避免这样做,因为它会造成混乱。最后,
 *         对于我们所设计的类,如果其实例包含同样属于这个类的其他实例,那么对这种
 *         无限递归要格外当心。
 */


public class Reluctant {
    /**
     * main 方法调用了 Reluctant 构造器,它将抛出一个异常。你可能期望 catch 子
     * 句能够捕获这个异常,并且打印 I told you so。凑近仔细看看这个程序就会发
     * 现,Reluctant 实例还包含第二个内部实例,它的构造器也会抛出一个异常。无
     * 论抛出哪一个异常,看起来 main 中的 catch 子句都应该捕获它,因此预测该程
     * 序将打印 I told you 应该是一个安全的赌注。但是当你尝试着去运行它时,就
     * 会发现它压根没有去做这类的事情:它抛出了 StackOverflowError 异常,为什
     * 么呢?
     * 与大多数抛出 StackOverflowError 异常的程序一样,本程序也包含了一个无限
     * 递归。当你调用一个构造器时,实例变量的初始化操作将先于构造器的程序体而
     * 运行[JLS 12.5]。在本谜题中, internalInstance 变量的初始化操作递归调用
     * 了构造器,而该构造器通过再次调用 Reluctant 构造器而初始化该变量自己的
     * internalInstance 域,如此无限递归下去。这些递归调用在构造器程序体获得
     * 执行机会之前就会抛出 StackOverflowError 异常,因为 StackOverflowError
     * 是 Error 的子类型而不是 Exception 的子类型,所以 catch 子句无法捕获它。
     */
    private Reluctant internalInstance = new Reluctant();

    /**
     * 声明将抛出异常的构造器,你需要注意,构造器必须声明其实例初始化操作会抛出的所有被检查异常。
     */
    private Reluctant() throws Exception {
        throw new Exception("I'm not coming out");
    }

    public static void main(String[] args) {
        try {
            // infinite loop
            Reluctant b = new Reluctant();
            System.out.println("Surprise!");
        } catch (Exception e) {
            System.out.println("I told you so");
        }
    }

    public class Car {

        private Class engineClass = Class.forName("com.test.shanemao.puzzler.Reluctant");

        // 必须显示地抛出ClassNotFoundException异常
        public Car() throws ClassNotFoundException {

        }
    }
}
