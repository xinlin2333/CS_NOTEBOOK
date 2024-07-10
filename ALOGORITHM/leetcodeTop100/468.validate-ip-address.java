/*
 * @lc app=leetcode id=468 lang=java
 *
 * [468] Validate IP Address
 */

// @lc code=start
class Solution {
    public String validIPAddress(String queryIP) {
        
        // case 1: IPV4;
        if(queryIP.contains(".")){
            String[] arr = queryIP.split(".");
            if (arr.length != 4) {
                return "Neither";
            }
            for (String str : arr) {
                
            }
        }
        // case 2 : IPV6;
        if (queryIP.contains(":")){

        }

        
    }
}
// @lc code=end

