package test.shanemao.puzzler;

import java.util.HashSet;
import java.util.Set;

/**
 * @author shane
 */

public class Name {
    private String first, last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Name)) {
            return false;
        }
        Name n = (Name) o;
        return n.first.equals(first) && n.last.equals(last);
    }

    /**
     * 山寨的equals方法，并不能真正覆盖equals方法。
     * 如果只写了这么一个equals方法，那么比较两个对象的时候，是调用父类Object
     * 的equals方法，该方法比较的是两个对象是否==
     */
    public boolean equals(Name n) {
        return n.first.equals(first) && n.last.equals(last);
    }

    /**
     * Name 类覆写了 equals 方法，而
     * hashCode 约定要求相等的对象要具有相同的散列码。为了遵守这项约定，无论
     * 何时， 只要你覆写了 equals 方法， 你就必须同时覆写 hashCode 方法[EJ Item 8]。
     */
    @Override
    public int hashCode() {
        // 不能使用super.hashCode();
//        return super.hashCode();
        return 37 * first.hashCode() + last.hashCode();
    }

    /**
     * 因为 Name 类没有覆写 hashCode 方法，所以它从 Object 那里继承了其 hashCode
     * 实现。这个实现返回的是基于标识的散列码。换句话说，不同的对象几乎总是产
     * 生不相等的散列值， 即使它们是相等的也是如此。 所以说 Name 没有遵守 hashCode
     * 的约定，因此包含 Name 元素的散列集合的行为是不确定的。
     * 当程序将第一个 Name 实例放置到散列集合中时，该集合就会在某个散列位置上
     * 放置这个实例对应的项。该集合是基于实例的散列值来选择散列位置的，这个散
     * 列值是通过实例的 hashCode 方法计算出来的。
     * 当该程序在检查第二个 Name 实例是否包含在散列集合中时，它基于第二个实例
     * 的散列值来选择要搜索的散列位置。因为第二个实例有别于第一个实例，因此它
     * 极有可能产生不同的散列值。如果这两个散列值映射到了不同的位置，那么
     * contains 方法将返回 false：我们所喜爱的啮齿动物米老鼠就在这个散列集合
     * 中，但是该集合却找不到他。
     * 假设两个 Name 实例映射到了相同的位置，那又会怎样呢？我们所了解的所有的
     * HashSet 实现都进行了一种优化，即每一项在存储元素本身之外，还存储了元素
     * 的散列值。在搜索某个元素时，这种实现通过遍历集合中的项，去拿存储在每一
     * 项中的散列值与我们想要查找的元素的散列值进行比较， 从而选取适当的散列位
     * 置。 只有在两个元素的散列值相等的情况下， 这种实现才会认为这两个元素相等。
     * 这种优化是有实际意义的，因为比较散列码相对于比较元素来说，其代价要小得
     * 多。
     * 对散列集合来说，这项优化并不足以使其能够搜索到正确的位置；两个 Name 实
     * 例必须具有相同的散列值才能让散列集合能够将它们识别为是相等的。 该程序偶
     * 尔也会打印出 true，这是因为被连续创建的两个对象偶尔也会具有相同的标识
     * 散列码。一个粗略的实验表明，这种偶然性出现的概率大约是 25,000,000 分之
     * 一。这个实验的结果可能会因所使用的 Java 实现的不同而有所变化，但是在任
     * 何我们所知的 JRE 上，你基本上是不可能看到该程序打印出 true 的。
     *
     * @param args
     */
    public static void main(String[] args) {
        Set s = new HashSet();
        s.add(new Name("Mickey", "Mouse"));
        // almost always false.
        // 如果只重写了equals方法，但是不重写hashCode方法（且不能仅仅调用super.hashCode()）
        // 那么两个对象几乎不会相等。
        System.out.println(
                s.contains(new Name("Mickey", "Mouse")));
    }
}
