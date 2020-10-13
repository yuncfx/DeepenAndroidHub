package simpletest;

import java.util.ArrayList;
import java.util.Vector;

public class TestSimple {
    public static void main(String[] args) {
//        int[] arr = {1, 2, 4, 6, 7};
//        int[] arr1 = {1, 2, 4, 6, 7}; // 这种形式等价于下面第三种形式，是简化形式
//        int[] arr2 = new int[] {1, 2, 4, 6, 7}; //静态初始化方式
//         
//         
//        System.out.println(arr == arr1); // false
//        System.out.println(arr1 == arr2); // false
        
        
        ArrayList<String> list = new ArrayList<>();
        list.addAll(null);
        System.out.println(list);
        
        
        list.trimToSize();
        
        Vector v = new Vector<>();
        v.trimToSize();
        
       
    }
}
