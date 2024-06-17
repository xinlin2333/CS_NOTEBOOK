/*
 * @lc app=leetcode id=55 lang=java
 *
 * [55] Jump Game
 */

// @lc code=start
class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length  == 0 ){
            return true;
        }
        int cover = 0;
        int len = nums.length;
        for (int i= 0;i<len;i++) {
            cover = Math.max(cover, nums[i]+i);
        }
        if(cover>= len-1) {
            return true;
        }
        return false;
    }
}
// @lc code=end

