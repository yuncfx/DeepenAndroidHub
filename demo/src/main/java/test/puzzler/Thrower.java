package test.puzzler;

/**
 * @author shane
 *         <p>
 *         在这个解决方案中将会发生许多微妙的事情。我们想要在构造器执行期间所抛出
 *         的异常不能作为一个参数传递给该构造器,因为 Class.newInstance 调用的是一
 *         个类的无参数构造器。因此,sneakyThrow 方法将这个异常藏匿于一个静态变量
 *         中。为了使该方法是线程安全的,它必须被同步,这使得对其的并发调用将顺序
 *         地使用静态域 t。
 *         要注意的是,t 这个域在从 finally 语句块中出来时是被赋为空的:这只是因为
 *         该方法虽然是卑鄙的,但这并不意味着它还应该是内存泄漏的。如果这个域不是
 *         被赋为空出来的,那么它阻止该异常被垃圾回收。最后,请注意,如果你让该方
 *         法抛出一个 InstantiationException 或是一个 IllegalAccessException 异常,
 *         它将以抛出一个 IllegalArgumentException 而失败。这是这项技术的一个内在
 *         限制。
 *         Class.newInstance 的文档继续描述道“Constructor.newInstance 方法通过将
 *         构造器抛出的任何异常都包装在一个(受检查的)InvocationTargetException
 *         异常中而避免了这个问题。”很明显,Class.newInstance 应该是做了相同的处
 *         理,但是纠正这个缺陷已经为时过晚,因为这么做将引入源代码级别的不兼容性,
 *         这将使许多依赖于 Class.newInstance 的程序崩溃。而弃用这个方法也不切实
 *         际,因为它太常用了。当你在使用它时,一定要意识到 Class.newInstance 可以
 *         抛出它没有声明过的受检查异常。
 */

public class Thrower {
    private static Throwable t;

    private Thrower() throws Throwable {
        throw t;
    }

    public static synchronized void sneakyThrow(Throwable t) {
        Thrower.t = t;
        try {
            Thrower.class.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            Thrower.t = null;
        }
    }
}

/**
 * 不受检查的转型”警告告诉你这个有问题的转型
 * 将不会在运行时刻受到检查。当你获得了一个不受检查的转型警告时,你应该修
 * 改你的程序以消除它,或者你可以确信这个转型不会失败。如果你不这么做,那
 * 么某个其他的转型可能会在未来不确定的某个时刻失败,而你也就很难跟踪此错
 * 误到其源头了。对于本谜题所示的情况,其情况更糟糕:在运行期抛出的异常可
 * 能与方法的签名不一致。sneakyThrow2 方法正是利用了这一点。
 */
class TigerThrower<T extends Throwable> {
    public static void sneakyThrow(Throwable t) {
        new TigerThrower<Error>().sneakyThrow2(t);
    }

    private void sneakyThrow2(Throwable t) throws T {
        throw (T) t;
    }
}
