/*
 * @lc app=leetcode id=643 lang=java
 *
 * [643] Maximum Average Subarray I
 */

// @lc code=start
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        if(nums.length < k) {
            return 0.00000;
        }
        int left = 0;
        int right = 0;
        double maxAvg = -Double.MAX_VALUE;
        double sum = 0;
        while(right < nums.length) {
            sum += nums[right];
            while (right - left + 1 >= k) {
                maxAvg = Math.max(maxAvg, sum/k);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return maxAvg;
    }
}
// @lc code=end

