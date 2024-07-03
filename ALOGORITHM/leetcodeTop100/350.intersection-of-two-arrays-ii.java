/*
 * @lc app=leetcode id=350 lang=java
 *
 * [350] Intersection of Two Arrays II
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int left = 0;
        int right = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        List<Integer> list = new ArrayList<>();
        while( left < len1 && right < len2  ) {
            int num1 = nums1[left];
            int num2 = nums2[right];
            if (num1 == num2) {
                list.add(num1);
                left++;
                right ++;
            }else if(num1 < num2) {
                left++;
            }else {
                right++;
            }
        } 
        int[] res = new int[list.size()];
        for (int i=0;i<list.size();i++) {
            res[i] = list.get(i);
        }
        return  res;
    }
}
// @lc code=end

