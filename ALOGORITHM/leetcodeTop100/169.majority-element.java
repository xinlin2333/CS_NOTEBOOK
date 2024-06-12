/*
 * @lc app=leetcode id=169 lang=java
 *
 * [169] Majority Element
 */

// @lc code=start

import java.util.Map;

class Solution {
    public int majorityElement(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int count = nums.length / 2+1;
        Map<Integer,Integer> hm = new HashMap<>();
        for (int i=0;i<nums.length;i++) {
            hm.put(nums[i], hm.getOrDefault(nums[i],0)+1);
            if (hm.get(nums[i]) >= count) {
                return nums[i];
            }
        }
        return 0;
    }
}
// @lc code=end

