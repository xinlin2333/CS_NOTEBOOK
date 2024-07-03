/*
 * @lc app=leetcode id=209 lang=java
 *
 * [209] Minimum Size Subarray Sum
 */

// @lc code=start
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        while(right < nums.length) {
            sum += nums[right];
            while ( sum >= target) {
                minLen = Math.min(minLen, right - left+1);
                sum -= nums[left];
                left++;
            }
        
            right++;
        }
        return minLen == Integer.MAX_VALUE? 0: minLen;

    }
}
// @lc code=end

