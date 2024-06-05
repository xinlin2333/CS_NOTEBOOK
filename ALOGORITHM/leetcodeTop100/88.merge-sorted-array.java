/*
 * @lc app=leetcode id=88 lang=java
 *
 * [88] Merge Sorted Array
 */

// @lc code=start
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {  
        int left = 0;
        int right = 0;
        int[] res = new int[m+n];
        int idx = 0;
        // merge sort 
        while (left<m && right <n ) {
            if (nums1[left] <= nums2[right]) {
                res[idx] = nums1[left];
                left++;
                
            }else{
                res[idx] = nums2[right];
                right++;
            }
            idx++;
        }
        if (left < m) {
            while(left <m) {
                res[idx] = nums1[left];
                idx++;
                left++;
            }
        }
        if (right < n ) {
            while(right < n) {
                res[idx] = nums2[right];
                idx++;
                right++;
            }
        }
        for (int i = 0; i < m + n; i++) {
            nums1[i] = res[i];
        }
    }
    public void mergeWithoutSpaceCost(int[] nums1, int m,int[] nums2, int n) {
        int left = m-1;
        int right = n-1;
        int idx = m+n-1;
        while(left>=0 && right >=0) {
            if (nums1[left]>nums2[right]) {
                nums1[idx] = nums1[left];
                left--;
            }else {
                nums1[idx] = nums2[right];
                right--;
            }
            idx--;
        }
        while(right >= 0 ) {
            nums1[idx] = nums2[right];
            right--;
            idx--;
        }
    }
}
// @lc code=end

