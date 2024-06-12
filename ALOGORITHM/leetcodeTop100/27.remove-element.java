/*
 * @lc app=leetcode id=27 lang=java
 *
 * [27] Remove Element
 */

// @lc code=start
class Solution {
    public int removeElement(int[] nums, int val) {
        if(nums.length == 0) {
            return 0;
        }
        int left = 0;

        for (int i=0;i<nums.length;i++) {
            if(nums[i]!=val) {
                nums[left++]= nums[i];
            }
        }
        return left;
    }
}
// @lc code=end

