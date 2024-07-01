/*
 * @lc app=leetcode id=567 lang=java
 *
 * [567] Permutation in String
 */

// @lc code=start

import java.util.Map;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() == 0 && s2.length() ==0 )  {
            return true;
        } 
        if(s1.length() > s2.length()) {
            return false;
        }
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for(int i=0;i<s1.length();i++) {
            need.put(s1.charAt(i), need.getOrDefault(s1.charAt(i), 0)+1);
        }
        int left, right = 0;
        int valid = 0;
        while(right<s2.length()) {
            char c = s2.charAt(right);
            right++;
            if(need.containsKey(c)){
                window.put(c, window.getOrDefault(c, 0)+1);
                if(window.get(c).equals(windowneed.get(c))){
                    valid++;
                }
            }
            while(right-left >= s1.length()){
                if (valid = need.size()){
                    return true;
                }
                char del = s1.charAt(left);
                left++;
                if (need.containsKey(del)){
                    if (window.get(del).equals(need.get(del))){
                        valid--;
                    }
                    window.put(del, window.getOrDefault(del, 0)-1);
                }
            }
        }
        return false;
    }
}
// @lc code=end

