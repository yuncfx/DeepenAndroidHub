package test.puzzler;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * @author shane
 *         puzzler 65
 *         尽管理论上说， 数组中的 100 个随机数有可能彼此都相等， 但是这种奇特现象发生的非常小：
 *         232×99 分之一，即大约 5×10953 分之一。
 *         <p>
 *         不要使用基于减法的比较器，除非你能够确保要比较的数值之间的差永远不会大于 Integer.MAX_VALUE
 */

public class SuspiciousSort {
    public static void main(String[] args) {
        Random rnd = new Random();
        Integer[] arr = new Integer[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rnd.nextInt();
        }
        /**
         Arrays.sort 方法已经存在许多年了，它工
         作得非常好。现在只有一个地方能够发现 bug 了：比较器。乍一看，这个比较器
         似乎不可能出错。毕竟，它使用的是标准的惯用法：如果你有两个数字，你想得
         到一个数值，其符号表示它们的顺序，那么你可以计算它们的差。这个惯用法至
         少从 1970 年代早期就一直存在了，它在早期的 UNIX 里面被广泛地应用。遗憾的
         是，这种惯用法从来都没有正确地工作过。本谜题也许应该称为“白痴一般的惯
         用法的案例”。 这种惯用法的问题在于定长的整数没有大到可以保存任意两个同
         等长度的整数之差的程度。当你在做两个 int 或 long 数值的减法时，其结果可
         能会溢出，在这种情况下我们就会得到错误的符号。
         */
        Comparator<Integer> cmp = (i1, i2) -> i2 - i1;
        Arrays.sort(arr, cmp);

        // 几乎都是打印 UNORDERED
        System.out.println(order(arr));

        Comparator<Integer> cmp2 = (i1, i2) -> (i2 < i1 ? -1 : (i2 == i1 ? 0 : 1));
        Arrays.sort(arr, cmp2);
        System.out.println(order(arr));
        // 或者使用reverseOrder方法
        Arrays.sort(arr, Collections.reverseOrder());
    }

    enum Order {
        // 升序
        ASCENDING,
        // 降序
        DESCENDING,
        // 相等
        CONSTANT,
        // 未排序
        UNORDERED
    }

    private static Order order(Integer[] a) {
        boolean ascending = false;
        boolean descending = false;
        for (int i = 1; i < a.length; i++) {
            ascending |= a[i] > a[i - 1];
            descending |= a[i] < a[i - 1];
        }
        if (ascending && !descending) {
            return Order.ASCENDING;
        }
        if (descending && !ascending) {
            return Order.DESCENDING;
        }
        if (!ascending) {
            // All elements equal
            return Order.CONSTANT;
        }
        // Array is not sorted
        return Order.UNORDERED;
    }
}
