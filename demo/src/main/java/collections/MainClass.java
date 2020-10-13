package collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class MainClass {
    static void fill(Set s) {
        s.addAll(Arrays.asList("one two three four five six seven".split(" ")));
    }

    public static void test(Set s) {
        System.out.println(s.getClass().getName());
        System.out.println(s.getClass().getName().replaceAll("\\w+\\.", ""));
        fill(s);
        fill(s);
        fill(s);
        System.out.println(s); // No duplicates!

        s.addAll(s);
        s.add("one");
        s.add("one");
        s.add("one");
        System.out.println(s);

        System.out.println("s.contains(\"one\"): " + s.contains("one"));
    }

    public static void main(String[] args) {
        test(new HashSet());
        test(new TreeSet());
        test(new LinkedHashSet());
        
        TreeSet set = new TreeSet<>(Arrays.asList(3, 2, 4, 1, 5));
        set.descendingIterator();
        
        
    }
}