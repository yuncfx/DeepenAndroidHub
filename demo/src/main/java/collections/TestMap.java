package collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

public class TestMap {

    public static void main(String[] args) {
        Map<String, int[]> map = new TreeMap<String, int[]>();

        int[] array = new int[3];
        array[0] = 0;
        array[1] = 1;
        array[2] = 2;
        map.put("array", array);

        Iterator<String> iter = map.keySet().iterator();

        while (iter.hasNext()) {
            String arrayName = iter.next();
            array = map.get(arrayName);
            System.out.print(arrayName + ":");
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i]);
            }
        }
    }
    
    @Test
    public void sortOnvalues() {
        Map<String, String> yourMap = new HashMap<String, String>();
        yourMap.put("1", "one");
        yourMap.put("2", "two");
        yourMap.put("3", "three");

        Map<String, Object> map = new LinkedHashMap<String, Object>();

        List<String> keyList = new ArrayList<String>(yourMap.keySet());
        List<String> valueList = new ArrayList<String>(yourMap.values());
        Set<String> sortedSet = new TreeSet<String>(valueList);
        
        Object[] sortedArray = sortedSet.toArray();
        int size = sortedArray.length;

        for (int i = 0; i < size; i++) {
          map.put(keyList.get(valueList.indexOf(sortedArray[i])), sortedArray[i]);
        }

        Set ref = map.keySet();
        Iterator it = ref.iterator();

        while (it.hasNext()) {
          String i = (String) it.next();
          System.out.println(i);
        }
    }
}
