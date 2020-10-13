package simpletest;

import org.junit.Test;

public class TestString {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        System.out.println(getReverseString("Hello World!"));
    }

    private static String getReverseString(String str) {

        if (str.length() <= 1) {
            return str;
        }

        return getReverseString(str.substring(1)) + str.charAt(0);
    }

    @Test
    public void testNull() {
        String s = "" + null;
        String s2 = null + "";
        System.out.println(s);
        System.out.println(s2);

        String[] str = new String[]{null};
        System.out.println(str[0]);
    }
}
