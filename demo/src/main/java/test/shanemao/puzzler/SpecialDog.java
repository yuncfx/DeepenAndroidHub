package test.shanemao.puzzler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author shane
 *         <p>
 *         问题在于,Dog 扩展了 Exception,
 *         而 Exception 实现了 java.io.Serializable。这就意味着 Dog 是可序列化的(serializable),并且解序列(deserialization)会创建一个隐藏的构造器。
 *         正如下面的这段程序所演示的,如果你序列化了 Dog.INSTANCE,然后对得到的
 *         字节序列(byte sequence)进行解序列,最后你就会得到另外一个 Dog。该程
 *         序打印的是 false,表示新的 Dog 实例和原来的那个实例是不同的,并且它还打
 *         印了 Woof,说明新的 Dog 实例也具有相应的功能:
 */

public class SpecialDog extends Exception {
    public static final SpecialDog INSTANCE = new SpecialDog();

    private SpecialDog() {
    }

    /**
     * 可在 Dog 中添加一个 readResolve 方法,它可以将那个隐藏的
     * 构造器转变为一个隐藏的静态工厂(static factory),以返回原来那个的 Dog
     * [EJ Items 2,57]。在 Dog 中添加了这个方法之后,CopyDog 将打印 true 而不是
     * false,表示那个“复本”实际上就是原来的那个实例:
     */
    private Object readResolve() {
        // Accept no substitutes
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Woof";
    }

    public static void main(String[] args) {
        SpecialDog newDog = (SpecialDog) deepCopy(SpecialDog.INSTANCE);
        // false
        System.out.println(newDog == SpecialDog.INSTANCE);
        System.out.println(newDog);
    }

    /**
     * This method is very slow and generally a bad idea!
     */
    static public Object deepCopy(Object obj) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            new ObjectOutputStream(bos).writeObject(obj);
            ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
            return new ObjectInputStream(bin).readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }
}
