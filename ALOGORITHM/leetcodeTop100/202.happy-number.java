/*
 * @lc app=leetcode id=202 lang=java
 *
 * [202] Happy Number
 */

// @lc code=start

import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer > numSet = new HashSet<>();
        while( n!= 1 && !numSet.contains(n)) {
            numSet.add(n);
            n = getSumofSquare(n);
        }
        return n == 1;
        
    }
    public int getSumofSquare(int n) {
        int sum = 0;
        while ( n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n = n / 10 ;
        }
        return sum;
    }
}
// @lc code=end

