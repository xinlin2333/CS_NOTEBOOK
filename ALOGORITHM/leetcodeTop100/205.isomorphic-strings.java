/*
 * @lc app=leetcode id=205 lang=java
 *
 * [205] Isomorphic Strings
 */

// @lc code=start
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if( t.length() == 0 && s.length() == 0 ){
            return true;
        }
        if (t.length() != s.length()) {
            return false;
        }
        int[] idxS = new int[200];
        int[] idxT = new int[200];
        for (int i=0;i<s.length();i++) {
            if(idxS[s.charAt(i)]!=idxT[t.charAt(i)]){
                return false;
            }
            idxS[s.charAt(i)] = i+1;
            idxT[t.charAt(i)] = i+1;
        }
        return true;
    }
}
// @lc code=end

