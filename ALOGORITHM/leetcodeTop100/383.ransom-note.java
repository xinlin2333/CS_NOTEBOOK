/*
 * @lc app=leetcode id=383 lang=java
 *
 * [383] Ransom Note
 */

// @lc code=start
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() == 0 && magazine.length() == 0) {
            return true;
        }
        if (magazine.length() == 0) {
            return false;
        } 
        HashMap<Character,Integer> hm = new HashMap<>();
        for(int i=0; i<magazine.length();i++) {
            char c = magazine.charAt(i);
            hm.put(c, hm.getOrDefault(c, 0)+1);
        } 
        for(int i=0;i<ransomNote.length();i++) {
            char c = ransomNote.charAt(i);
            if(!hm.constainsKey(c) || hm.get(c)== 0) {
                return false;
            }
            hm.put(c, hm.getOrDefault(c, 0)-1);
            
        }
        return true; 
    }
}
// @lc code=end

