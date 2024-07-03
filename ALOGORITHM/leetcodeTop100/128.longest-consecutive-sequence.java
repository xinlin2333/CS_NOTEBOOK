/*
 * @lc app=leetcode id=128 lang=java
 *
 * [128] Longest Consecutive Sequence
 */

// @lc code=start

import java.util.HashSet;

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } 
        HashSet<Integer> numSet = new HashSet<>();
        for (int i =0; i<nums.length; i++) {
            numSet.add(nums[i]);
        } 
        int ans = 0;
        for (int num : numSet) {
            if (!numSet.contains(num-1))  {
                int curNum = num; 
                int count = 1;
                while(numSet.contains(curNum +1)){
                    count++;
                    curNum++;
                }
                ans = Math.max(ans, count);
            }
              
        }
        return ans;
    }
}
// @lc code=end

