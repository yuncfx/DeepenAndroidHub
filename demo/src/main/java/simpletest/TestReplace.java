package simpletest;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class TestReplace {
    
    public static final int A = 0;
    public static final int B = 1;

    public static void main(String[] args) {
        String str = "111[[haha]]sdfa[[world]]";
        
        int leftIndex =  str.indexOf("[[");
        int rightIndex = str.indexOf("]]");
        System.out.println( "first ="+ leftIndex + ", rightIndex=" + rightIndex);
        
        
        String ss = str.replace("\\[\\[.+\\]\\]", "999");
        
        boolean b = str.matches("\\[{2}.+\\]{2}");
        System.out.println(b);
        
        
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(11);
        int min = atomicInteger.accumulateAndGet(10, Integer::min);
        System.out.println(min);
        
        String email = "[a-zA-Z_0-9[@][\\.][\\s][\\']]*";
        boolean isEmail = Pattern.matches(email, " ".toString());
        
        System.out.println(isEmail);
        
        switch (leftIndex) {
        case A:
            
            break;

        default:
            break;
        }
    }
}
