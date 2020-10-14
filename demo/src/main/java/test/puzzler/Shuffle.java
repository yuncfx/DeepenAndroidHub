package test.puzzler;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Shuffle {
    private static Random rnd = new Random();

    public static void shuffle(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            swap(a, i, rnd.nextInt(a.length));
        }
    }

    private static void swap(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void shuffleEquitable(Object[] a) {
        Collections.shuffle(Arrays.asList(a));
    }

    /**
     * 最基础情况,让我们观察长度为 0
     * 的数组,这显然是公平的。根据归纳法的步骤,如果你将这个方法用在一个长度
     * n>0 的数组上,它会为这个数组的 0 位置上的元素随机选择一个值。然后,它会
     * 遍历数组剩下的元素:在每个位置上,它会在“子数组”中随机选择一个元素,
     * 这个子数组从当前位置开始到原数组的末尾。对于从位置 1 到原数组末尾的这个
     * 长度为 n-1 的子数组来说,如果将该方法作用在这个子数组上,它实际上也是在
     * 做上述的事。这就完成了证明。它同时也提供了 shuffle 方法的递归形式,它的
     * 细节就留给读者作为练习了。
     */
    public static void shuffleEquitable2(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            swap(a, i, i + rnd.nextInt(a.length - i));
        }
    }
}