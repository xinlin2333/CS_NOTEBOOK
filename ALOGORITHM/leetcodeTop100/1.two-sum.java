/*
 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 */

// @lc code=start
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if(nums.length == 0) {
            return null;
        } 
        for (int i=0;i<nums.length-1;i++) {
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        return res; 
    }
}
// @lc code=end

