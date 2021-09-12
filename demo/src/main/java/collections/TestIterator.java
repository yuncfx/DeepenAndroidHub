package collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

public class TestIterator {

    public static void main(String[] args) {
    }

    /**
     * While Iterator only supports remove(), ListIterator adds set() and add()
     * methods.
     * 
     * Using the nextIndex() and previousIndex() methods allows you to find out
     * the index of the elements around the current cursor position:
     */
    @Test
    public void testListIterator() {
        ArrayList<String> aList = new ArrayList<String>();
        aList.add("1");
        aList.add("2");
        aList.add("3");
        aList.add("4");
        aList.add("5");

        Iterator<String> iterator = aList.iterator();
        while (iterator.hasNext()) {
            String item = (String) iterator.next();
        }

        ListIterator<String> listIterator = aList.listIterator();

        System.out.println("Previous: " + listIterator.previousIndex());
        System.out.println("Next: " + listIterator.nextIndex());

        // advance current position by one using next method
        listIterator.next();
        System.out.println("Previous: " + listIterator.previousIndex());
        System.out.println("Next: " + listIterator.nextIndex());

        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }

    }

    /**
     * Test listIterator add()
     */
    @Test
    public void testListIteratorOperate() {
        ArrayList<String> aList = new ArrayList<String>();
        aList.add("1");
        aList.add("2");
        aList.add("3");
        aList.add("4");
        aList.add("5");

        ListIterator<String> listIterator = aList.listIterator();
        listIterator.next();
        // listIterator��add��������뵽��ǰλ�ã�����ָ���ָ������ӵ�Ԫ�أ�next֮�󣬻�������ӵ���Ԫ��
        listIterator.add("Added Element");

        for (String str : aList) {
            System.out.println(str);
        }

        System.out.println("---���Ƿָ���---");

        System.out.println("next : " + listIterator.next()); // 2
        System.out.println("next : " + listIterator.next()); // 3

        // remove element returned by last next method
        listIterator.remove();// �Ƴ����ǵ�ǰָ��ָ���λ��
        listIterator.next();
        /**
         * Replaces the last element returned by {@link #next} or
         * {@link #previous} with the specified element (optional operation).
         * This call can be made only if neither {@link #remove} nor
         * {@link #add} have been called after the last call to {@code next} or
         * {@code previous}. set������next��previous����֮����ã�
         * ���������forѭ��������һ��Ҳ�����������Ϊָ�벻֪��ָ��ȥ�ˣ�
         */
        listIterator.set("100");
        for (String str : aList) {
            System.out.println(str);
        }
        System.out.println("---���Ƿָ���---");

        List list = new LinkedList();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        // �˴�indexָ������listIterator����ʼλ�ã�
        int index = list.size() - 2;
        // ��ǰiterָ�����λ��2(list.size()-2)�� nextָ�����C(ʵ��λ��Ϊ2)�� previousָ�����B
        ListIterator iter = list.listIterator(index);

        while (iter.hasPrevious()) {
            System.out.println(iter.previous()); // B
            // ��a����B��ǰ��
            iter.add("a");
            break;
        }

        while (iter.hasNext()) {
            System.out.println(iter.next()); // ���û�������ѭ������ָ�����C
            // ��a����iter.next()�ĺ���
            iter.add("a");
            break;
        }
        System.out.println(list);

    }

    /**
     * ��û����ʾ����iterator������£���Ȼ�ᱨ�쳣�� Exception in thread "main"
     * java.util.ConcurrentModificationException at
     * java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901) at
     * java.util.ArrayList$Itr.next(ArrayList.java:851) at
     * month_09_collections.TestIterator.main(TestIterator.java:24)
     */
    @Test
    public void testException() {
        List<String> list = new ArrayList<String>();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("C");
        list.add("C");
        list.add("C");
        list.add("C");

        for (String s : list) {
            if (s.equals("B")) {
                list.remove("B");
            }
            System.out.println(s);
        }
    }

    /**
     * ʹ�õ���iterator.remove()����, ���û���쳣
     */
    @Test
    public void testRemove() {
        ArrayList<String> aList = new ArrayList<String>();

        aList.add("1");
        aList.add("2");
        aList.add("3");
        aList.add("4");
        aList.add("5");

        for (String str : aList) {
            System.out.println(str);
        }
        Iterator<String> itr = aList.iterator();

        // remove 2 from ArrayList using Iterator's remove method.

        String strElement = "";

        while (itr.hasNext()) {
            strElement = (String) itr.next();
            if (strElement.equals("2")) {
                itr.remove();
                break;
            }
        }
        for (String str : aList) {
            System.out.println(str);
        }
    }

}
