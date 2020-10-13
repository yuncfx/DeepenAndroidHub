package simpletest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

public class ConcurrentListExample {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        // get the iterator
        Iterator<String> it = list.iterator();

        // manipulate list while iterating
        while (it.hasNext()) {
            System.out.println("list is:" + list);
            String str = it.next();
            System.out.println(str);
            //throw ConcurrentModificationException
            if (str.equals("2"))
                list.remove("5");
            //throw ConcurrentModificationException
            if (str.equals("3"))
                list.add("3 found");
            // below code don't throw ConcurrentModificationException
            // because it doesn't change modCount variable of list
            if (str.equals("4"))
                list.set(1, "4");
        }
    }

    @Test
    public void main() {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        // get the iterator
        Iterator<String> it = list.iterator();

        // manipulate list while iterating
        while (it.hasNext()) {
            System.out.println("list is:" + list);
            String str = it.next();
            System.out.println(str);
            // 注意下面的代码只是影响到了list， 但是iterator的数据不会变化。
            if (str.equals("2"))
                list.remove("5");
            if (str.equals("3"))
                list.add("3 found");
            // below code don't throw ConcurrentModificationException
            // because it doesn't change modCount variable of list
            if (str.equals("4"))
                list.set(1, "4");
        }
        
    }
}
