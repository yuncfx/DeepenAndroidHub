package collections;

import java.util.List;
import java.util.Vector;

import org.junit.Test;

public class TestVector {
    public static void main(String[] args) {

    }

    @Test
    public void test1() {
        Vector v = new Vector(5);
        for (int i = 0; i < 10; i++) {
            v.add(i, 0);
        }
        System.out.println(v);

        for (int i = 0; i < 10; i++) {
            v.insertElementAt(i, 0);
        }

        System.out.println(v.capacity()); // 20
        System.out.println(v);
        /**
         * When removing all the elements from a vector, the capacity does not
         * change
         */
        v.clear();

        System.out.println(v);
        System.out.println(v.capacity()); // 20

        v.trimToSize();
        System.out.println(v.capacity()); // 0
    }

    /**
     * change size with the setSize() method.
     * 
     * 如果设置size小于当前的元素数量，则元素会从尾部开始被丢掉 如果设置size大于当前的元素数量，则用null补充
     * setSize(0)则移除所有的元素 size为0或者没有元素，则isEmpty为true
     */
    @Test
    public void test2() {
        Vector v = new Vector(5);
        for (int i = 0; i < 10; i++) {
            v.add(0, i);
        }
        System.out.println(v);

        v.setSize(3);

        System.out.println(v);

        v.setSize(10);
        System.out.println(v); // [9, 8, 7, null, null, null, null, null, null,
                               // null]

        // 保证空间达到40
        v.ensureCapacity(40);
        System.out.println(v.capacity());
    }

    @Test
    public void testCopyInto() {
        Vector v1 = new Vector();
        v1.add("A");
        v1.add("B");
        v1.add("C");
        String array[] = new String[v1.size()];
        v1.copyInto(array);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    /**
     * If you replace the element within the sublist, it is replaced in the
     * original vector.
     * 
     * If you remove something from the sublist, it is also removed from the
     * vector.
     */
    @Test
    public void testSubList() {
        Vector v1 = new Vector();
        v1.add("A");
        v1.add("B");
        v1.add("C");
        List l = v1.subList(1, 2);

        l.remove(0);

        System.out.println(l);
        System.out.println(v1);
    }

}
