package simpletest;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

public class TestTreeSet {
    //static NavigableSet<String> citiesSet;

    public static void main(String[] args) {
        TreeSet<String> tSet = new TreeSet<String>();
        tSet.add("1");
        tSet.add("2");
        tSet.add("3");
        tSet.add("4");
        tSet.add("5");
        SortedSet<String> sortedSet = tSet.headSet("3");
        System.out.println("Head Set Contains : " + sortedSet);

        System.out.println("Lowest value Stored in Java TreeSet is : " + tSet.first());
        System.out.println("Highest value Stored in Java TreeSet is : " + tSet.last());

        SortedSet<String> sortedSet2 = tSet.subSet("2", "5");

        System.out.println("SortedSet Contains : " + sortedSet2);

        SortedSet<String> sortedSet3 = tSet.tailSet("2");
        System.out.println("Tail Set Contains : " + sortedSet3);

        System.out.println("Tail Set from \"\" contains " + tSet.tailSet(""));

        String elements[] = { "A", "C", "D", "G", "F" };
        TreeSet<String> set = new TreeSet<>(Arrays.asList(elements));
        System.out.println(set);
        System.out.println(set.tailSet("C"));
        System.out.println(set.headSet("C"));
        // \0 表示字符串结尾，空白， 用在这里的作用是包含
        System.out.println(set.headSet("C\0"));
        System.out.println(set.tailSet("C\0"));
        System.out.println(set.subSet("C", "F\0"));
        System.out.println(set.subSet("C", "C\0"));
        System.out.println(set.subSet("C", "C"));

        try {
            String last = set.last();
            boolean first = true;
            while (true) {
                if (!first) {
                    System.out.print(", ");
                }
                System.out.println(last);
                last = set.headSet(last).last();
            }
        } catch (NoSuchElementException e) {
            System.out.println();
        }
        
        /*
        String[] cities = { "A", "B", "C", "D", "E", "F" };

        citiesSet = new TreeSet<String>();
        for (String city : cities)
            citiesSet.add(city);

        for (String city : citiesSet.descendingSet())
            System.out.println("  " + city);
            */
    }
}
