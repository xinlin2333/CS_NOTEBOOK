package ALOGORITHM.JIANZHI;

import java.util.Stack;

/**
 * @author canoeYang
 * @Date 2020-08-30 20:57
 */
public class judgeStackWithPushPop31 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed.length!=popped.length){
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int cur = 0;
        for(int i=0;i<pushed.length;i++){
            stack.push(pushed[i]);
            while(!stack.isEmpty()&&stack.peek()==popped[cur]){
                stack.pop();
                cur++;
            }
        }
        return stack.isEmpty();
    }
}
