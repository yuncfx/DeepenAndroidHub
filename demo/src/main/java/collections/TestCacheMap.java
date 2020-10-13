package collections;

public class TestCacheMap {
    public static void main(String[] args) {
        CacheMap cm = new CacheMap(10, 10);
        cm.put("hello0", 100);
        cm.put("hello1", 1001);
        cm.put("hello2", 1002);
        cm.put("hello3", 1003);
        cm.put("hello4", 1004);
        cm.put("hello5", 1005);
        Object matchStartsWith = cm.matchStartsWith("hello");
        System.out.println(matchStartsWith);
    }
}
