/*
 * @lc app=leetcode id=2824 lang=java
 *
 * [2824] Count Pairs Whose Sum is Less than Target
 */

// @lc code=start

import java.util.List;

class Solution {
    public int countPairs(List<Integer> nums, int target) {
        if (nums.size() <2) {
            return 0;
        }
        int res = 0;
        for (int i=0;i<nums.size()-1;i++) {
            for(int j=1;j<nums.size();j++) {
                if(nums.get(i)+nums.get(j) < target) {
                    res++;
                }
            }
        }
        return res;
    }
}
// @lc code=end

