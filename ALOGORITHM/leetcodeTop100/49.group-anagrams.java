/*
 * @lc app=leetcode id=49 lang=java
 *
 * [49] Group Anagrams
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs.length == 0) {
            return null;
        }  
        HashMap<String, List<String>> map  = new HashMap<>();
        for (int i=0;i<strs.length;i++) {
            String str = strs[i];
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String newStr = String.valueOf(ch);
            if(map.containsKey(newStr)) {
                map.get(newStr).add(str);
            }else{
                List<String> tmp = new ArrayList<>();
                tmp.add(str);
                map.put(newStr, tmp);
            }
        }
        return new ArrayList<>(map.values());
    }
}
// @lc code=end

