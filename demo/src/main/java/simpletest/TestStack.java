package simpletest;

import java.util.Stack;

public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        System.out.println("is empty = " + stack.isEmpty());
        
        stack.push("hello");
        stack.push("world");
        stack.push("hello");
        stack.push("people1");
        stack.push("people2");
        stack.push("people3");
        
        stack.add("people4");
        stack.add("people5");
        stack.remove("people5");
        
        System.out.println("pop = " + stack.pop()); // pop方法将栈顶的数据出栈并返回
        System.out.println("peek = " + stack.peek()); //peek方法返回栈顶的数据，但是不出栈
        System.out.println("search index = " + stack.search("world"));
        System.out.println("stack = " + stack);
    }
}
