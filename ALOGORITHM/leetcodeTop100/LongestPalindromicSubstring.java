package ALOGORITHM.leetcodeTop100;

/**
 * @author canoeYang
 * @Date 2020-08-06 10:50
 */
public class LongestPalindromicSubstring {
    /**
     * 暴力解法
     * 枚举所有长度大于等于2的子串，依次判断是否为回文子串
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s.length()<2){
            return "";
        }
        int maxLen =1;
        int start =0;
        char[] charArr = s.toCharArray();
        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<s.length();j++){
                if(j-i+1 > maxLen && validPalindromic(charArr,i,j)){
                    maxLen = i-j+1;
                    start = i;
                }
            }

        }
        return s.substring(start,start+maxLen);
    }
    public boolean validPalindromic(char[] charArr,int left,int right){
        while(left<right){
            if(charArr[left]!= charArr[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public String longestPalindromeWithDp(String s){
        if(s.length()<2){
            return s;
        }
        int len = s.length();
        char[] charArr = s.toCharArray();
        boolean[][] dp = new boolean[len][len];
        for(int i=0;i<len;i++){
            dp[i][i] = true;
        }
        int maxLen =1;
        int start = 0;
        for(int j=1;j<len;j++){
            for(int i=j-1;i>=0;i--){
                if(charArr[i]!=charArr[j]){
                    dp[i][j] = false;
                }else{
                    if(j-i<3){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                if(j-i+1>maxLen && dp[i][j]){
                    maxLen = j-i+1;
                    start = i;
                }
            }
        }
        return s.substring(start,start+maxLen);
    }
}
