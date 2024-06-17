/*
 * @lc app=leetcode id=9 lang=java
 *
 * [9] Palindrome Number
 */

// @lc code=start

import java.util.ArrayList;

class Solution {
    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        int n = str.length();
        for(int i=0;i<n/2;i++){
            if(str.charAt(i)!=str.charAt(n-i-1)){
                return false;
            }
        }
        return true;
    }
}
// @lc code=end

