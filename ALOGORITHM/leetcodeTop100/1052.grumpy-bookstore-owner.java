/*
 * @lc app=leetcode id=1052 lang=java
 *
 * [1052] Grumpy Bookstore Owner
 */

// @lc code=start
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        if (customers.length == 0) {
            return 0;
        }    
        int[] s = new int[2];
        int maxS = 0;
        for (int i=0; i<customers.length; i++) {
            s[grumpy[i]] += customers[i];
            if (i < minutes -1) {
                continue;
            }
            maxS = Math.max(maxS, s[1]);
            s[1] -= grumpy[i-minutes+1] > 0 ? customers[i-minutes+1]: 0;

        }
        return maxS + s[0]; 
    }
}
// @lc code=end

