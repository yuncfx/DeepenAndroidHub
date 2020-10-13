package simpletest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class TestMethod {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<String>();

        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("3");

        Vector<String> v = new Vector<String>();

        v.add("A");
        v.add("B");
        v.add("D");
        v.add("E");
        v.add("F");
        v.add("G");
        v.add("H");

        System.out.println(v);
        Collections.copy(v, arrayList);
        System.out.println(v);

        Object obj = Collections.max(arrayList);
        System.out.println("max = " + obj); //5
        obj = Collections.min(arrayList);
        System.out.println("min = " +obj); //1

        Enumeration<String> e = Collections.enumeration(arrayList);

        while (e.hasMoreElements())
            System.out.print(" " + e.nextElement()); // 1 2 4 5 3
        System.out.println();

        Collections.sort(arrayList);
        System.out.println("Sorted ArrayList contains : " + arrayList); //[1, 2, 3, 4, 5]
        int index = Collections.binarySearch(arrayList, "4"); //3
        System.out.println("Element found at : " + index);

/*        Collections.fill(arrayList, "REPLACED");
        System.out.println(arrayList);*/

        Collections.replaceAll(arrayList, "1", "Replace All"); 
        System.out.println("replaceAll " + arrayList); // [Replace All, 2, 3, 4, 5]

        Collections.reverse(arrayList);
        System.out.println("reverse " + arrayList); // [5, 4, 3, 2, Replace All]

        Collections.shuffle(arrayList);
        System.out.println("shuffle " + arrayList);// random order

        Collections.swap(arrayList, 0, 4);
        System.out.println(arrayList);

        Comparator<String> comparator = (Comparator) Collections.reverseOrder();
        Collections.sort(arrayList, comparator);
        System.out.println(arrayList);

        List numbers = new ArrayList();

        for (int i = 0; i < 25; i++) {
            numbers.add(i);
        }

        System.out.println(Arrays.toString(numbers.toArray()));

        Collections.rotate(numbers, 5);// 把尾部的n个数平移到前部

        System.out.println(Arrays.toString(numbers.toArray()));

        List<String> colours = new ArrayList<String>();
        colours.add("red");
        colours.add("green");
        colours.add("blue");
        colours.add("yellow");
        colours.add("cyan");
        colours.add("white");
        colours.add("black");

        Collections.sort(colours);
        System.out.println(Arrays.toString(colours.toArray()));

        List list = Collections.EMPTY_LIST;
        Set set = Collections.EMPTY_SET;
        Map map = Collections.EMPTY_MAP;

        List<String> s = Collections.emptyList();
        Set<Long> l = Collections.emptySet();
        Map<Date, String> d = Collections.emptyMap();
        System.out.println(list + ": " + set + " : " + map + " : " + s + " : " + l + " : " + d );
        
        
        List<Character> list2 = new ArrayList<Character>();
        list2.add('X');
        System.out.println("Element added to list: " + list2.get(0));
        Collection<Character> immutableCol = Collections.unmodifiableCollection(list2);
        // UnsupportedOperationException
        immutableCol.add('Y');
    }
}

