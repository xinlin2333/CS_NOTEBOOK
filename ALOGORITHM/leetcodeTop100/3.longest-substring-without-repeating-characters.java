/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 */

// @lc code=start

import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        int maxLen = 0;
        while ( right < s.length()) {
            char ch = s.charAt(right); 
            map.put(ch, map.getOrDefault(ch, 0)+1);

            while(map.get(ch)> 1) {
               char leftch = s.charAt(left);
               map.put(leftch, map.get(leftch)-1);
               if(map.get(leftch) == 0) {
                map.remove(leftch);
               }
               left++;

            }
            maxLen = Math.max(maxLen, right-left+1);
            right ++;
        }
        return maxLen;
    }
}
// @lc code=end

