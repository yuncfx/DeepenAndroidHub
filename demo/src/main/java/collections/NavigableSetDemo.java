package collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class NavigableSetDemo {

    static NavigableSet<String> citiesSet;

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 2, 4, 1, 5);
        NavigableSet<Integer> ns = new TreeSet<Integer>(list);
 
        System.out.println("Ascending order (default): " + ns); // Ascending order (default): [1, 2, 3, 4, 5]

        // 以逆序形式输出
        Iterator<Integer> descendingIterator = ns.descendingIterator();
        StringBuilder sb = new StringBuilder("Descending order: ");
        while (descendingIterator.hasNext()) {
            int m = descendingIterator.next();
            sb.append(m + " ");
        }
        System.out.println(sb); // Descending order: 5 4 3 2 1

        int greatest = ns.lower(3);
        // 小于3的最大数
        System.out.println("Lower of 3 = " + greatest); // Lower of 3 = 2

        int smallest = ns.higher(3);
        // 大于3的最小数
        System.out.println("Higher of 3 = " + smallest); // Higher of 3 = 4

        String[] cities = { "A", "B", "C", "D", "E", "F", "O" };

        citiesSet = new TreeSet<String>();
        for (String city : cities)
            citiesSet.add(city);

        // 大于等于A的最小数
        System.out.println(citiesSet.ceiling("A")); // A
        // 小于等于P的最大数
        System.out.println(citiesSet.floor("P"));// O

    }
}