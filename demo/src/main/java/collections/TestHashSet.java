package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestHashSet {
    public static void main(String[] args) {
        String elements[] = { "A", "B", "C", "D", "E" };
        Set<String> set = new HashSet<>(Arrays.asList(elements));

        // clone，需要将set先强转成HashSet，因为set没有clone方法
        Set<String> set2 = (Set) ((HashSet) set).clone();

        List<String> stuff = Arrays.asList(new String[] { "a", "b" });

        List<String> list = new ArrayList<>(stuff);
        // 将list转化为不可修改的list
        list = Collections.unmodifiableList(list);

        try {
            list.set(0, "new value");
        } catch (UnsupportedOperationException e) {

        }
        // 将set转化为不可修改的set
        set = Collections.unmodifiableSet(set);

        Map<String, String> map = new HashMap<>();
        // 将map转化为不可修改的map
        map = Collections.unmodifiableMap(map);

    }
}
