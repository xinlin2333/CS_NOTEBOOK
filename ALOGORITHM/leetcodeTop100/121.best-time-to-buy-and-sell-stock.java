/*
 * @lc app=leetcode id=121 lang=java
 *
 * [121] Best Time to Buy and Sell Stock
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
       if(prices.length == 0) {
        return 0;
       }
       int ans = 0;
       int minPrice = prices[0];
       for (int price: prices) {
        ans = Math.max(ans, price-minPrice);
        minPrice = Math.min(minPrice, price);
       }
       return ans;
    }
}
// @lc code=end

