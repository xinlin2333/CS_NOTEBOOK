/*
 * @lc app=leetcode id=1343 lang=java
 *
 * [1343] Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
 */

// @lc code=start
class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        if(arr.length < k) {
            return 0;
        }
        int ans = 0;
        int sum = 0;
        int left = 0;
        int right = 0;
        while(right<arr.length) {
            sum += arr[right];
            if ( right - left + 1 >= k) {
                if (sum >= threshold*k) {
                    ans++;
                }
                sum -= arr[left];
                left++;
                
            }
            right++;
        }
        return ans;
        
    }
}
// @lc code=end

