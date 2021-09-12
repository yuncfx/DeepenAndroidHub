package collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class TestSet {

    public static void main(String[] args) {
        
    }

    // Ĭ��ʹ������
    @Test
    public void testTreeSet() {
        Set<String> set = new TreeSet<String>();

        set.add("b");
        set.add("c");
        set.add("a");

        Iterator it = set.iterator();
        while (it.hasNext()) {

            Object element = it.next();
            System.out.println(element);
        }

        // ת����ָ�����͵����飬������String
        String[] array = set.toArray(new String[set.size()]);
        Arrays.toString(array);
    }

    // ά�ֲ����˳��
    @Test
    public void testLinkedHashSet() {
        Set<String> set = new LinkedHashSet<String>();

        // Add some elements
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("2");
        
        // List the elements
        for (Iterator it = set.iterator(); it.hasNext();) {
            Object o = it.next();
        }
    }

}

class Product implements Comparable<Product> {

    @Override
    public int compareTo(Product o) {
        return 0;
    }

}