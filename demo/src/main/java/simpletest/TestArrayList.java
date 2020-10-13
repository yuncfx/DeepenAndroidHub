package simpletest;

import java.util.ArrayList;

public class TestArrayList {
    public static void main(String[] args) {
        ArrayList<String> list= new ArrayList<>();
            
        list.add(0, "hello world");
        
        for (String x : list) {
            System.out.println(x);
        }
    }
}
