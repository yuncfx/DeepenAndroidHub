package test.demo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo {
    @Test
    public void test() {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(16);
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
    }
}
