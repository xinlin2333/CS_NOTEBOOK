package ALOGORITHM.leetcodeTop100;

/**
 * @author canoeYang
 * @Date 2020-08-04 10:33
 */

import java.util.Stack;

/**
 * 最小栈:每次获取的栈顶元素都是最小的
 * 思路：两个栈，一个栈存放数据，一个栈存放最小值，要么push当前值，要么再次push 最小栈中的最小值
 * 空间复杂度：O()
 */
public class MinStack {
    Stack<Integer> data ;
    Stack<Integer> minStack ;

    /** initialize your data structure here. */
    public MinStack() {
        data = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        data.push(x);
        if(minStack.empty() || minStack.peek()>x){
            minStack.push(x);
        }else{
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        if(!data.isEmpty()){
            data.pop();
            minStack.pop();
        }
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
/**
 * 时间复杂度为1;
 * 用栈存储数据；其次对于当前的最小值需要进行处理，每次插入时，先判断插入值与当前值的大小，如果min>x，说明插入x之前的最小值是min，但是之后是x
 * 这里需要尽心更新；
 * 删除的时候需要去判断当前pop的值是否和min相等，相等话需要更新min值应该是pop,pop
 */
class MinStack1 {
    Stack<Integer> data ;
    private int min = Integer.MAX_VALUE;

    /** initialize your data structure here. */
    public MinStack1() {
        data = new Stack<>();
    }

    public void push(int x) {
       if(min >= x){
           data.push(min);
           min = x;

       }
       data.push(x);
    }

    public void pop() {
        if(min == data.pop()){
            min = data.pop();
        }
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return min;
    }
}
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

