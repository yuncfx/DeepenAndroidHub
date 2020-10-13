package collections;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import java.util.NavigableMap;
import java.util.TreeMap;

public class NavigableMapDemo {
    public static void main(String[] args) {

    }
    
    @Test
    public void test1() {
        NavigableMap<Integer, String> map = new TreeMap<Integer, String>();
        map.put(2, "two");
        map.put(1, "one");
        map.put(3, "three");
        System.out.println("Original map: " + map + "\n");

        Entry firstEntry = map.pollFirstEntry();
        System.out.println("First entry: " + firstEntry);
        System.out.println("After polling the first entry: " + map + "\n");

        Entry lastEntry = map.pollLastEntry();
        System.out.println("Last entry:" + lastEntry);
        System.out.println("After polling last entry:" + map);
    }

    @Test
    public void test2() {
        Calendar now = Calendar.getInstance();
        Locale locale = Locale.getDefault();

        Map<String, Integer> names = now.getDisplayNames(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
        NavigableMap<String, Integer> nav = new TreeMap<String, Integer>(names);
        System.out.printf("Whole list:%n%s%n", nav);
        System.out.printf("First key: %s\tFirst entry: %s%n", nav.firstKey(), nav.firstEntry());
        System.out.printf("Map before Sunday: %s%n", nav.navigableKeySet());
    }
}