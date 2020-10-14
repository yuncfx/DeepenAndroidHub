package test.puzzler;

class Jeopardy {
    public static final String PRIZE = "$64,000";
}

/**
 * @author shane
 *         <p>
 *         可以证明，final 修饰符对方法和域而言，意味着某些完全不同的事情。对于方
 *         法，final 意味着该方法不能被覆写（对实例方法而言）或者隐藏（对静态方法
 *         而言）[JLS 8.4.3.3]。对于域，final 意味着该域不能被赋值超过一次[JLS
 *         8.3.1.2]。关键字相同，但是其行为却完全不相关。
 */
public class DoubleJeopardy extends Jeopardy {
    public static final String PRIZE = "2 cents";

    public static void main(String[] args) {
        System.out.println(DoubleJeopardy.PRIZE);
    }
}