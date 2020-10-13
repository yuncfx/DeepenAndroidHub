package test.shanemao.puzzler;

import java.util.Calendar;

/**
 * @author shane
 *         让我们来
 *         看看其细节。Elvis 类的初始化是由虚拟机对其 main 方法的调用而触发的。首
 *         先，其静态域被设置为缺省值[JLS 4.12.5]，其中 INSTANCE 域被设置为 null，
 *         CURRENT_YEAR 被设置为 0。接下来，静态域初始器按照其出现的顺序执行。第一
 *         个静态域是 INSTANCE，它的值是通过调用 Elvis()构造器而计算出来的。
 *         这个构造器会用一个涉及静态域 CURRENT_YEAR 的表达式来初始化 beltSize。通
 *         常，读取一个静态域是会引起一个类被初始化的事件之一，但是我们已经在初始
 *         化 Elvis 类了。 递归的初始化尝试会直接被忽略掉[JLS 12.4.2, 第 3 步]。 因此，
 *         CURRENT_YEAR 的值仍旧是其缺省值 0。这就是为什么 Elvis 的腰带尺寸变成了
 *         -1930 的原因。
 *         <p>
 *         最后，从构造器返回以完成 Elvis 类的初始化，假设我们是在 2006 年运行该程
 *         序，那么我们就将静态域 CURRENT_YEAR 初始化成了 2006。遗憾的是，这个域现
 *         在所具有的正确值对于向 Elvis.INSTANCE.beltSize 的计算施加影响来说已经
 *         太晚了，beltSize 的值已经是-1930 了。这正是后续所有对
 *         Elvis.INSTANCE.beltSize()的调用将返回的值。
 */

public class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    private final int beltSize;
    /**
     * final 类型的域只有在其初始化表达式是常量表达式时才是常量
     */
    private static final int CURRENT_YEAR =
            Calendar.getInstance().get(Calendar.YEAR);

    private Elvis() {
        beltSize = CURRENT_YEAR - 1930;
    }

    public int beltSize() {
        return beltSize;
    }

    public static void main(String[] args) {
        // Elvis wears a size -1930 belt.
        System.out.println("Elvis wears a size " +
                INSTANCE.beltSize() + " belt.");
    }
}
