package collections;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

import org.junit.Test;

public class TestWeakHashMap {
    private static WeakHashMap<String, String> map;
    
    public static void main(String[] args) {
        map = new WeakHashMap<>();
        map.put("A", "B");
        Runnable runner = new Runnable() {
            public void run() {
                while (map.containsKey("A")) {
                    try {
                        Thread.sleep(300);
                        System.gc();
                    } catch (InterruptedException ignored) {
                    }
                    System.out.println("Has A");
                    System.gc();
                }
            }
        };
        Thread t = new Thread(runner);
        t.start();
        System.out.println("Main waiting");
        try {
            t.join();
        } catch (InterruptedException ignored) {
        }
        System.gc();
    
    }
    
    
    @Test
    public void test2() {
        Object keyObject = "";
        Object valueObject = "";
        Map<Object, Object> weakMap = new WeakHashMap<>();
    
        WeakReference<Object> weakValue = new WeakReference<>(valueObject);
    
        weakMap.put(keyObject, weakValue);
    
        Iterator<Object> it = weakMap.keySet().iterator();
        while (it.hasNext()) {
          Object key = it.next();
          weakValue = (WeakReference<Object>) weakMap.get(key);
          if (weakValue == null) {
            System.out.println("Value has been garbage-collected");
          } else {
            System.out.println("Get value");
            valueObject = weakValue.get();
          }
        }
        
    }
}
