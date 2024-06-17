/*
 * @lc app=leetcode id=45 lang=java
 *
 * [45] Jump Game II
 */

// @lc code=start
class Solution {
    public int jump(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i=1;i<len;i++) {
            dp[i] = dp[i-nums[i]]+1;
        }
        return dp[len-1];
        
    }
}
// @lc code=end

