package simpletest;

import java.util.ArrayList;

public class TestValue {
    
    private ArrayList<String> mHello = new ArrayList<>();
    
    private ArrayList<String> mLocal = null;
    
    public static void main(String[] args) {
        
        
        TestValue tv = new TestValue();
        
        tv.mLocal = tv.getList();
        
        for(String str : tv.mLocal) {
            System.out.println(str);
        }
        
        tv.mLocal.set(0, "not hello");
        
        for(String str : tv.mLocal) {
            System.out.println(str);
        }
        
        for(String str : tv.mHello) {
            System.out.println(str);
        }
        
    }
    
    public ArrayList<String> getList() {
        
        mHello.add("hello");
        mHello.add("world");
        mHello.add("fuck");
        mHello.add("bug");
        
        return mHello;
    }
}
