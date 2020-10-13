package simpletest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class TestCheckGroups {
    public static void main(String[] args) {

    }

    private static List<String> CONSTANT_LEFT = new ArrayList<>();
    private static List<String> CONSTANT_RIGHT = new ArrayList<>();

    static {
        CONSTANT_LEFT.add("(");
        CONSTANT_LEFT.add("[");
        CONSTANT_LEFT.add("{");

        CONSTANT_RIGHT.add(")");
        CONSTANT_RIGHT.add("]");
        CONSTANT_RIGHT.add("}");
    }

    public static boolean groupCheck(String s) {
        if (s == null || s.length() == 0)
            return true;

        Stack<String> stack = new Stack<>();

        String temp = null;
        for (int i = 0; i < s.length(); i++) {
            temp = s.charAt(i) + "";

            if (CONSTANT_LEFT.contains(temp)) {
                stack.push(temp);
            } else if (CONSTANT_RIGHT.contains(temp)) {

                if (stack.isEmpty()) {
                    return false;
                }

                String pop = stack.pop();

                if (CONSTANT_RIGHT.indexOf(temp) != CONSTANT_LEFT.indexOf(pop)) {
                    return false;
                }
            }
        }

        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

    @Test
    public void myTests() {
        // assertEquals(TestCheckGroups.groupCheck("()"), true);
        // assertEquals(TestCheckGroups.groupCheck("({"), false);
        boolean isTrue = TestCheckGroups.groupCheck(")))");
        System.out.println(isTrue);
    }

}
