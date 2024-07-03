/*
 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 */

// @lc code=start

import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
       if ( nums.length ==0 ) {
        return null;
       }
       HashMap<Integer, Integer> map = new HashMap<>();
       for(int i=0;i<nums.length;i++) {
        int num = target- nums[i];
        if(map.containsKey(num)) {
            return new int[]{map.get(num), i};
        }
        map.put(nums[i], i);
       }
       return new int[]{};
    }
}
// @lc code=end

