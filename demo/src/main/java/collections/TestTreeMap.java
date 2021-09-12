package collections;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

public class TestTreeMap {
    public static void main(String[] args) {

    }

    /**
     * firstKey, lastKey 通过这两个方法，可以快速的访问头部的key和尾部的key
     */
    @Test
    public void test1() {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("1", "One");
        treeMap.put("2", "Two");
        treeMap.put("3", "Three");
        treeMap.put("4", "Four");
        treeMap.put("5", "Five");

        System.out.println("Lowest key: " + treeMap.firstKey());
        System.out.println("Highest key: " + treeMap.lastKey());
    }

    /**
     * 逆向遍历一个TreeMap
     * 
     * SortedSet headMap(Object toKey) 
     * Map headMap = map.headMap(toKey+"\0"); //
     * 这样也能包含toKey这个key 
     * SortedSet tailMap(Object fromKey) // 注意，因为比较的是fromKey和每一个key的大小（Comparator的compare方法或者自身的compareTo方法，因此这个
     * fromKey不一定要在map的所有key中。） 
     * SortedSet subMap(Object fromKey, Object toKey) 
     * fromKey <= map keys < toKey
     */
    @Test
    public void test2() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        String key = map.lastKey();
        boolean first = true;
        while (map.headMap(key) != null) {
            if (!first) {
                System.out.print(",");
            }

            System.out.print(key);

            key = map.headMap(key).lastKey();
            first = false;

        }

    }

    /**
     * SortedMap Contains : {2=Two, 3=Three, 4=Four}
     */
    @Test
    public void testSubMap() {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();

        treeMap.put("1", "One");
        treeMap.put("2", "Two");
        treeMap.put("3", "Three");
        treeMap.put("4", "Four");
        treeMap.put("5", "Five");

        SortedMap<String, String> sortedMap = treeMap.subMap("2", "5");
        System.out.println("SortedMap Contains : " + sortedMap);
    }

    /**
     * Tail Map Contains : {2=Two, 3=Three, 4=Four, 5=Five}
     */
    @Test
    public void testTailMap() {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("1", "One");
        treeMap.put("3", "Three");
        treeMap.put("2", "Two");
        treeMap.put("5", "Five");
        treeMap.put("4", "Four");

        SortedMap<String, String> sortedMap = treeMap.tailMap("2");
        System.out.println("Tail Map Contains : " + sortedMap);
        
        sortedMap = treeMap.tailMap("40");
        System.out.println("Tail Map Contains : " + sortedMap); //{5=Five}
    }

    /**
     * headMap
     */
    @Test
    public void test3() {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("1", "One");
        treeMap.put("3", "Three");
        treeMap.put("2", "Two");
        treeMap.put("5", "Five");
        treeMap.put("4", "Four");

        SortedMap<String, String> sortedMap = treeMap.headMap("3");
        System.out.println("Head Map Contains : " + sortedMap);
    }

    @Test
    public void test4() {
        TreeMap<String, String> treeMap = new TreeMap<>();
        Map<String, String> map = Collections.synchronizedMap(treeMap);
        System.out.println(map);
    }

    /**
     * higherKey(), lowerKey()
     */
    @Test
    public void test5() {
        TreeMap<Integer, Product> db = new TreeMap<Integer, Product>();
        db.put(1000, new Product("D", 350));
        db.put(1011, new Product("p", 15.75));
        db.put(1102, new Product("M", 8.50));
        db.put(2023, new Product("A", 150));
        db.put(2034, new Product("T", 9.99));

        System.out.println(db.subMap(1000, 1999) + "\n");

        System.out.println(db.tailMap(1011) + "\n");

        System.out.println(db.headMap(2023));

        System.out.println("First key higher than 2034: " + db.higherKey(2034));
        System.out.println("First key lower than 2034: " + db.lowerKey(2034));
    }

    class Product {
        String desc;

        double price;

        Product(String desc, double price) {
            this.desc = desc;
            this.price = price;
        }

        public String toString() {
            return "Description=" + desc + ", Price=" + price;
        }
    }

}
