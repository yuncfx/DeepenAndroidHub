package test.puzzler;

import java.util.*;
import java.io.*;

/**
 * @author shane
 *         puzzler91
 *         <p>
 *         序列化和反序列化一个 Sub 实例会产生一个被破坏的副本。为什么呢?阅读程序
 *         并不会帮助你找出原因,因为真正引起问题的代码在其他地方。错误是由
 *         HashSet 的 readObject 方法引起的。在某些情况下,这个方法会间接地调用某
 *         个未初始化对象的被覆写的方法。为了组装(populate)正在被反序列化的散列集
 *         合,HashSet.readObject 调用了 HashMap.put 方法,而它会去调用每个键(key)
 *         的 hashCode 方法。由于整个对象图(object graph)正在被反序列化,并没有
 *         什么可以保证每个键在它的 hashCode 方法被调用的时候已经被完全初始化了。
 *         实际上,这很少会成为一个问题,但是有时候它会造成绝对的混乱。这个缺陷会
 *         在正在被反序列化的对象图的某些循环中出现。
 *         <p>
 *         为了更具体一些,让我们看看程序中在反序列化 Sub 实例的时候发生了什么。首
 *         先,序列化系统会反序列化 Sub 实例中 Super 的域。唯一的这样的域就是 set,
 *         它包含了一个对 HashSet 的引用。在内部,每个 HashSet 实例包含一个对 HashMap
 *         的引用,HashMap 的键是该散列集合的元素。HashSet 类有一个 readObject 方法,
 *         它创建一个空的 HashMap,并且使用 HashMap 的 put 方法,针对集合中的每个元
 *         素在 HashMap 中插入一个键-值对。put 方法会调用键的 hashCode 方法以确定它
 *         所在的单元格(bucket)。在我们的程序中,散列映射表中唯一的键就是 Sub
 *         的实例,而它的 set 域正在被反序列化。这个实例的子类域(subclass field),
 *         即 id,尚未被初始化,所以它的值为 0,即所有 int 域的缺省初始值。不幸的是,
 *         Sub 的 hashCode 方法将返回这个值,而不是最后保存在这个域中的值 666。因为
 *         hashCode 返回了错误的值,相应的键-值对条目将会放入错误的单元格中。当 id
 *         域被初始化为 666 时,一切都太迟了。当 Sub 实例在 HashMap 中的时候,改变这
 *         个域的值就会破坏这个域,进而破坏 HashSet,破坏 Sub 实例。程序检测到了这
 *         个情况,就报告出了相应的错误。
 *         <p>
 *         这个程序说明,包含了 HashMap 的 readObject 方法的序列化系统总体上违背了
 *         不能从类的构造器或伪构造器(pseudoconstructor)中调用其可覆写方法的规则[EJ Item 15]。Super 类的(缺省的)readObject 方法调用了 HashSet 的(显
 *         式的)readObject 方法,该方法进而调用了它内部的 HashMap 的 put 方法,put
 *         方法又调用了 Sub 实例的 hashCode 方法,而该实例正处在创建的过程中。现在
 *         我们遇到大麻烦了:Super 类中,从 Object 类继承而来的 hashCode 方法在 Sub
 *         中被覆写了,但是这个被覆写的方法在 Sub 的域被初始化之前就被调用了,而该
 *         方法需要依赖于 Sub 的域。
 *         这个问题和谜题 51 中的那个本质上几乎是完全相同的。唯一真正不同的是在这
 *         个谜题中,readObject 伪构造器错误地替代了构造器。HashMap 和 Hashtable
 *         的 readObject 方法受到的影响是类似的。
 */
public class SerialKiller {
    public static void main(String[] args) {
        Sub sub = new Sub(666);
        sub.checkInvariant();
        Sub copy = (Sub) deepCopy(sub);
        copy.checkInvariant();
    }

    /**
     * Copies its argument via serialization (See Puzzle 80)
     */
    static public Object deepCopy(Object obj) {
        try {
            ByteArrayOutputStream bos =
                    new ByteArrayOutputStream();
            new ObjectOutputStream(bos).writeObject(obj);
            ByteArrayInputStream bin =
                    new ByteArrayInputStream(bos.toByteArray());
            return new ObjectInputStream(bin).readObject();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}

class Super implements Serializable {
    final Set<Super> set = new HashSet<>();
}

final class Sub extends Super {
    private int id;


    public Sub(int id) {
        this.id = id;
        // Establish invariant
        set.add(this);
    }

    public void checkInvariant() {
        if (!set.contains(this)) {
            throw new AssertionError("invariant violated");
        }
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Sub) && (id == ((Sub) o).id);
    }
}