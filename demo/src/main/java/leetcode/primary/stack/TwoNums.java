package leetcode.primary.stack;

import java.util.Stack;

public class TwoNums {

    public static void main(String[] args) {
        System.out.println(isValid("(])"));
    }

    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        if (s == null) {
            return false;
        }

        if (s.isEmpty()) {
            return true;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                Character peek = stack.peek();
                if (peek == null) {
                    return false;
                }
                switch (c) {
                    case ')':
                        if (peek == '(') {
                            stack.pop();
                        } else {
                            return false;
                        }
                        break;
                    case ']':
                        if (peek == '[') {
                            stack.pop();
                        } else {
                            return false;
                        }
                        break;
                    case '}':
                        if (peek == '{') {
                            stack.pop();
                        } else {
                            return false;
                        }
                        break;
                    default:
                        break;
                }

            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
