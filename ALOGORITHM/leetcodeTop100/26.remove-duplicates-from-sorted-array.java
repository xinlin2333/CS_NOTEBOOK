/*
 * @lc app=leetcode id=26 lang=java
 *
 * [26] Remove Duplicates from Sorted Array
 */

// @lc code=start
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 1;
        while(right < nums.length) {
            if (nums[left]!=nums[right]){
                left++;
                nums[left] = nums[right];
                
            }
            right++;
        }
        return left+1;
    }
}
// @lc code=end

