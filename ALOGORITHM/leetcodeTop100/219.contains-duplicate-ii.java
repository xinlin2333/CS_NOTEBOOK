/*
 * @lc app=leetcode id=219 lang=java
 *
 * [219] Contains Duplicate II
 */

// @lc code=start

import java.util.HashMap;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return false;
        }  
        HashMap< Integer, Integer> map = new HashMap<>();
        for(int i=0; i< nums.length; i++) {
            if ( map.containsKey(nums[i])) {
                int idx = map.get(nums[i]);
                if (i - idx <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
// @lc code=end

