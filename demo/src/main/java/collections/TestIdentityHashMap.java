package collections;

import java.util.IdentityHashMap;
import java.util.Map;

public class TestIdentityHashMap {
    public static void main(String[] args) {
        Map<Object, String> objMap = new IdentityHashMap<>();
        
        Object o1 = new Integer(123);
        Object o2 = new Integer(123);
        objMap.put(o1, "first");
        objMap.put(o2, "second");
        System.out.println(o1.equals(o2));
        
        Object v1 = objMap.get(o1); // first
        Object v2 = objMap.get(o2); // second
        
        String str = "111";
        String str2 = "111";
        System.out.println(str == str2);// true
        
        objMap.put(str, "third");
        objMap.put(str2, "fourth");
        
        Object v3 = objMap.get(str);
        Object v4 = objMap.get(str2);
        System.out.println(v3); // fourth
        System.out.println(v4); // fourth
    }
}
