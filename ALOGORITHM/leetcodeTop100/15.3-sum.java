/*
 * @lc app=leetcode id=15 lang=java
 *
 * [15] 3Sum
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length < 3) {
            return null;
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        for(int i=0; i< len-2;i++) {
            if(i>0 && nums[i]== nums[i-1]) {
                continue;
            }
            int x = nums[i];
            int j= i+1;
            int k = len-1;
            while(j<k) {
                int sum = x + nums[j] + nums[k];
                if (sum == 0) {
                    res.add(List.of(x, nums[j],nums[k]));
                    for(++j; j<k &&nums[j]==nums[j-1]; ++j);
                    for(--k; k>j && nums[k]==nums[k+1];--k );
                }else if(sum > 0 ) {
                    k--;
                }else {
                    j++;
                }
            }
            return res;
        }

        
    }
}
// @lc code=end

