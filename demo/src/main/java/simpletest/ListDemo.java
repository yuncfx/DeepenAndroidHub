package simpletest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;
/**
Collection
    |-- List: 元素是有序的，元素可以重复，因为该集合体系有索引。
        |--ArrayList：底层的数据结构使用的是数组结构。特点：查询速度很快，但是增删稍慢，线程不同步[1.2版本出现]
           可变长度数组，每次创建的是延长50%新数组，把旧数组的数据放到新数组中
        |--LinkedList：底层使用的是链表数据结构。特点：增删速度很快，查询速度稍慢
        |--Vector：底层是数组数据结构。线程同步。无论增删还是查询都慢，被ArrayList替代了。[1.0版本出现]
            可变长度数组，每次延长100%
    |-- Set： 元素是无序的，元素不可以重复，该集合中没有索引。
List：
    List集合判断元素是否相同，依据的是元素的equals方法。在调用contains或者remove方法之前，会先调用equals方法
    特有方法：凡是可以操作角标的方法都是该体系特有的方法；
增
    add(index, element);
    addAll(index, Collection);
删
    remove(index);
    removeAll(Collection<?> c) 
改
    set(index, element);
查
    get(index);
    subList(from, to);
    listIterator();
*/

/**
 * List集合特有的迭代器，ListIterator是Iterator的子接口
 * 在迭代时，不可以通过集合对象的add和remove方法操作集合中的元素（专业的说法叫结构性发生改变，包括集合对象的add、
 * remove方法以及集合对象的长度发生了改变），会发生并发修改异常ConcurrentModificationException（注意这个异常并非100%
 * 发生，有些代码执行的时候 并不会出现该异常） 但是可以用set方法（set方法不属于结构性改变）。
 * 所以，在迭代时，只能用迭代器的方法操作元素，可是Iterator的方法是有限的， 只能对元素进行判断，取出，删除的操作
 * 如果想要其他的操作如添加，修改等，就需要使用其子接口ListIterator。 该接口只能通过List集合的listIterator()方法获取。
 */
public class ListDemo {
    public static void main(String[] args) {

        // 演示列表迭代器
        ArrayList<String> al = new ArrayList<>();
        al.add("java01");
        al.add("java02");
        al.add("java03");
        // 在迭代过程中，准备添加或者删除元素。

        Iterator<String> it = al.iterator();
        while (it.hasNext()) {

            Object obj = it.next();
            if (null != obj && obj.equals("java02")) {
                // 此处会引发并发修改异常ConcurrentModificationException，不能既用iterator又用，但是可以用角标的set操作。

                // al.add("java008"); // error, ConcurrentModificationException

                // it.remove(); // 将java02的引用从集合中删除，只有remove没有添加

                al.set(1, "java0002");
            }
            sop("obj=" + obj);
        }
        sop(al);

        ListIterator<String> li = al.listIterator();
        while (li.hasNext()) {
            Object obj = li.next();
            if (obj.equals("java01")) {
                // li.add("java009");
                li.set("java006");
            }
        }
        sop(al);
        while (li.hasPrevious()) {
            sop("pre:" + li.previous());
        }
        sop("hasNext():" + li.hasNext());
        sop("hasPrevious():" + li.hasPrevious());
        sop(al);
    }

    @Test
    public void method() {
        ArrayList<String> al = new ArrayList<String>();
        al.add("java01");
        al.add("java02");
        al.add("java03");
        sop("origin :" + al);
        al.add(1, "java09");
        sop("after :" + al);
        // delete
        // al.remove(2);
        // modify
        al.set(2, "java007");
        sop(al);
        // get element
        sop("get(1): " + al.get(1));
        // get all elements
        for (int x = 0; x < al.size(); x++) {
            sop("al(" + x + ")=" + al.get(x));
        }
        Iterator<String> it = al.iterator();
        while (it.hasNext()) {
            sop("it.next() = " + it.next());
        }
        // get object's position by indexOf
        sop("index = " + al.indexOf("java007"));
        List<String> sub = al.subList(1, 3);
        sop("sub=" + sub);
    }

    public static void sop(Object obj) {
        System.out.println(obj);
    }
}