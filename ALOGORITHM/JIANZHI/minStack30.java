package ALOGORITHM.JIANZHI;

import java.util.Stack;

/**
 * @author canoeYang
 * @Date 2020-08-30 20:16
 */
class MinStack {
    private Stack<Integer> minStack;
    private Stack<Integer> numStack;
    /** initialize your data structure here. */
    public MinStack() {
        minStack = new Stack<>();
        numStack = new Stack<>();
    }
    public void push(int x) {
        numStack.push(x);
        if(minStack.isEmpty() ||minStack.peek()>=x) {
            minStack.push(x);
        }
    }

    public void pop() {
        if(numStack.isEmpty()){
            return;
        }
        if(numStack.peek()==minStack.peek()){
            minStack.pop();
        }
        numStack.pop();

    }

    public int top() {
        if(numStack.isEmpty()){
            return Integer.MIN_VALUE;
        }
        return numStack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
