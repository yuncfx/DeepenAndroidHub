package simpletest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestNestedGenerics {
    public static void main(String[] args) {
        List<String> listOfStrings = new ArrayList<String>();
        listOfStrings.add("Hello again");
        List<List<String>> listOfLists = new ArrayList<List<String>>();
        listOfLists.add(listOfStrings);
        String s = listOfLists.get(0).get(0);
        System.out.println(s); // prints "Hello again"

        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        String value1 = map.get("key1");

        List<Father> fathers = new ArrayList<>();
        List<Child> children = new ArrayList<>();
        List<List<Father>> listofFathers = new ArrayList<>();
        listofFathers.add(fathers);
        // 类型错误，List<Father> 不是 List<Child>的父类
        // listofFathers.add(children);

    }
}