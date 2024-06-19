/*
 * @lc app=leetcode id=18 lang=java
 *
 * [18] 4Sum
 */

// @lc code=start

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums.length < 4) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for(int i=0;i<len-3;i++) {
            int x = nums[i];
            if(i>0&& nums[i-1]==nums[i]) {
                continue;
            }
            for (int j=i+1;j<len-2;j++) {
                int y = nums[j];
                if(j>0&&nums[j]==nums[j-1]) {
                    continue;
                }
                int k = j+1;
                int n = len-1;
                while(k<n) {
                    int sum = x + y + nums[k] + nums[n];
                    if(sum == target) {
                        res.add(List.of(x,y,nums[k],nums[n]));
                        for(++k; k<n && nums[k]==nums[k-1]; ++k);
                        for(++n; n> k && nums[n]==nums[n+1]; --n);

                    }else if(sum >target) {
                        n--;
                    }else {
                        k++;
                    }
                }
            }
           
        }
        return res;
    }
}
// @lc code=end

