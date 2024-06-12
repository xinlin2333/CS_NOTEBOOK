/*
 * @lc app=leetcode id=122 lang=java
 *
 * [122] Best Time to Buy and Sell Stock II
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
       if (prices.length < 2) {
        return 0;
       }
       int[][] dp = new int[prices.length][2];
       dp[0][0] = 0;
       dp[0][1] -= prices[0];
       for (int i=1;i<prices.length;i++) {
        // dp[i][0] no stock;
        dp[i][0] = Math.max(dp[i-1][1]+prices[i], dp[i-1][0]);
        // dp[i][1] have stock
        dp[i][1] = Math.max(dp[i-1][0]-prices[i], dp[i-1][1]);
       }
       return dp[prices.length-1][0];
    }
}
// @lc code=end

