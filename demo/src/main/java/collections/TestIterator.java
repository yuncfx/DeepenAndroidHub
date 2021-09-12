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
        // listIterator的add方法会插入到当前位置，并且指针会指向新添加的元素，next之后，会跳过添加的新元素
        listIterator.add("Added Element");

        for (String str : aList) {
            System.out.println(str);
        }

        System.out.println("---我是分割线---");

        System.out.println("next : " + listIterator.next()); // 2
        System.out.println("next : " + listIterator.next()); // 3

        // remove element returned by last next method
        listIterator.remove();// 移除的是当前指针指向的位置
        listIterator.next();
        /**
         * Replaces the last element returned by {@link #next} or
         * {@link #previous} with the specified element (optional operation).
         * This call can be made only if neither {@link #remove} nor
         * {@link #add} have been called after the last call to {@code next} or
         * {@code previous}. set必须在next或previous方法之后调用，
         * 否则下面的for循环遍历，一个也不会输出，因为指针不知道指哪去了？
         */
        listIterator.set("100");
        for (String str : aList) {
            System.out.println(str);
        }
        System.out.println("---我是分割线---");

        List list = new LinkedList();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        // 此处index指定的是listIterator的起始位置，
        int index = list.size() - 2;
        // 当前iter指向的是位置2(list.size()-2)， next指向的是C(实际位置为2)， previous指向的是B
        ListIterator iter = list.listIterator(index);

        while (iter.hasPrevious()) {
            System.out.println(iter.previous()); // B
            // 把a放在B的前面
            iter.add("a");
            break;
        }

        while (iter.hasNext()) {
            System.out.println(iter.next()); // 如果没有上面的循环，则指向的是C
            // 把a放在iter.next()的后面
            iter.add("a");
            break;
        }
        System.out.println(list);

    }

    /**
     * 在没有显示调用iterator的情况下，仍然会报异常： Exception in thread "main"
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
     * 使用的是iterator.remove()方法, 因此没有异常
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
