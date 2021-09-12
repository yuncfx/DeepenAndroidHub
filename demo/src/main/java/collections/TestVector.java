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
     * �������sizeС�ڵ�ǰ��Ԫ����������Ԫ�ػ��β����ʼ������ �������size���ڵ�ǰ��Ԫ������������null����
     * setSize(0)���Ƴ����е�Ԫ�� sizeΪ0����û��Ԫ�أ���isEmptyΪtrue
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

        // ��֤�ռ�ﵽ40
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
