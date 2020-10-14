package test.demo;

import org.junit.Test;

public class HashCodeDemo {
    @Test
    public void test() {
        String str = "jack";

        int i = (('j' * 31 + 'a') * 31 + 'c') * 31 + 'k';
        System.out.println(i);

        System.out.println(str.hashCode());
    }
}
