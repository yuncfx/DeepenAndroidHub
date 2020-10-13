package test.shanemao.puzzler;

/**
 * @author shane
 *         <p>
 *         类初始化是按照静态初始器在源代码中出现的顺序去执行这些初始器
 *         的。Cache 类有两个静态初始器:在类顶端的一个 static 语句块,以及静态域
 *         initialized 的初始化。静态语句块是先出现的,它调用了方法
 *         initializeIfNecessary,该方法将测试 initialized 域。因为该域还没有被赋
 *         予任何值,所以它具有缺省的布尔值 false。与此类似,sum 具有缺省的 int 值
 *         0。因此,initializeIfNecessary 方法执行的正是你所期望的行为,将 4,950
 *         添加到了 sum 上,并将 initialized 设置为 true。
 *         在静态语句块执行之后,initialized 域的静态初始器将其设置回 false,从而
 *         完成 Cache 的类初始化。遗憾的是,sum 现在包含的是正确的缓存值,但是
 *         initialized 包含的却是 false:Cache 类的两个关键状态并未同步。此后,Client 类的 main 方法调用 Cache.getSum 方法,它将再次调用
 *         initializeIfNecessary 方法。因为 initialized 标志是 false,所以
 *         initializeIfNecessary 方法将进入其循环,该循环将把另一个 4,950 添加到 sum
 *         上,从而使其值增加到了 9,900。getSum 方法返回的就是这个值,而程序打印的
 *         也是它。
 */

class Cache {
    static {
        initializeIfNecessary();
    }

    private static int sum;

    public static int getSum() {
        initializeIfNecessary();
        return sum;
    }

    private static boolean initialized = false;

    private static synchronized void initializeIfNecessary() {
        if (!initialized) {
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            initialized = true;
        }
    }
}

class Cache2 {
    private static final int sum = computeSum();

    private static int computeSum() {
        int result = 0;
        for (int i = 0; i < 100; i++) {
            result +=i;
        }
        return result;
    }

    public static int getSum() {
        return sum;
    }
}

public class Client {
    public static void main(String[] args) {
        // 9900
        System.out.println(Cache.getSum());
    }
}
