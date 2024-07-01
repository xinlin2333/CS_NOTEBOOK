/*
 * @lc app=leetcode id=1550 lang=java
 *
 * [1550] Three Consecutive Odds
 */

// @lc code=start
class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        if (arr.length < 3) {
            return false;
        }
        for (int i=0;i<= arr.length-3;i++) {
            if (arr[i] % 2 == 0) {
                continue;
            }
            if (arr[i] % 2 != 0 && arr[i+1] %2 != 0 && arr[i+2] % 2 !=0) {
                return true;
            }
            if (arr[i+1]%2 ==0 ){
                i++;
            }
            if (arr[i+2] % 2 == 0 ){
                i +=2 ;
            }
        }
        return false;
    }
}
// @lc code=end

