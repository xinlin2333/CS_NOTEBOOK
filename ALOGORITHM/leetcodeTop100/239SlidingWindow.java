package ALOGORITHM.leetcodeTop100;

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length==0){
            return new int[0];
        }
        int len = nums.length;
        int[] res = new int[len-k+1];
        Deque<Integer> queue = new LinkedList<>();
        for(int i=0;i<len;i++){
            while(!queue.isEmpty() && nums[queue.peekLast()]<nums[i]){
                queue.pollLast();
            }
            queue.addLast(i);
            if(queue.peek()<=i-k){
                queue.poll();
            }
            if(i-k+1>=0){
                res[i-k+1] = nums[queue.peek()];
            }
        }
        return res;
    }

    // dp solution
    public int[] maxSlidingWindowWithDp(int[] nums, int k){
        int len = nums.length;
        if(len * k ==0){
            return new int[0];
        }
        if( len ==1){
            return nums;
        }
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = nums[0];
        right[len-1]= nums[len-1];
        for(int i=0;i<len;i++){
            if(i%k==0){
                left[i] = nums[i];
            }else{
                left[i] = Math.max(nums[i],left[i-1]);
            }
            int j = len-i-1;
            if((j+1)%k==0){
                right[j] = nums[j];
            }else{
                right[j] = Math.max(right[j+1],nums[j]);
            }
        }
        int[] res = new int[len-k+1];
        for(int i=0;i<len-k+1;i++){
            res[i] = Math.max(left[i+k-1],right[i]);
        }
        return res;

    }
}