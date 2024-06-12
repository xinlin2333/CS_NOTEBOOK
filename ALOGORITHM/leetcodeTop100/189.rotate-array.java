/*
 * @lc app=leetcode id=189 lang=java
 *
 * [189] Rotate Array
 */

// @lc code=start
class Solution {
    public void rotate(int[] nums, int k) {
       int len = nums.length;
       k = k % len;
       reverse(nums,0, len-1);
       reverse(nums, 0,k-1);
       reverse(nums,k,len-1);
    }
    public void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }

    }
}
// @lc code=end

