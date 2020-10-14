package structure.leetcode.stack;

import java.util.Stack;

public class _232_stackImplQueue {
	private Stack<Integer> inStack;
	private Stack<Integer> outStack;

    /** Initialize your data structure here. */
    public _232_stackImplQueue() {
    	inStack = new Stack<>();
    	outStack = new Stack<>();
    }
    
    /** 入队 */
    public void push(int x) {
        inStack.push(x);
    }
    
    /** 出队 */
    public int pop() {
    	checkOutStack();
    	return outStack.pop();
    }
    
    /** 获取队头元素 */
    public int peek() {
    	checkOutStack();
    	return outStack.peek();
    }
    
    /** 是否为空 */
    public boolean empty() {
    	return inStack.isEmpty() && outStack.isEmpty();
    }
    
    private void checkOutStack() {
    	if (outStack.isEmpty()) {
        	while (!inStack.isEmpty()) {
        		outStack.push(inStack.pop());
        	}
        }
    }
}
