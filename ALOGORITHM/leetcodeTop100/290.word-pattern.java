/*
 * @lc app=leetcode id=290 lang=java
 *
 * [290] Word Pattern
 */

// @lc code=start

import java.util.HashMap;

class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if(words.length != pattern.length()) {
            return false;
        }
        HashMap<Character, String>  c2s = new HashMap<>();
        HashMap<String, Character> s2c = new HashMap<>();

        for(int i=0;i<words.length;i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            if(c2s.containsKey(c)) {
                if(!c2s.get(c).equals(word)) {
                    return false;
                }
            }else {
                if (s2c.containsKey(word)) {
                    if(!s2c.get(word).equals(c)){
                        return false;
                    }
                }
                c2s.put(c, word);
                s2c.put(word, c);
            }
        }
        return true;
        
    }
}
// @lc code=end

