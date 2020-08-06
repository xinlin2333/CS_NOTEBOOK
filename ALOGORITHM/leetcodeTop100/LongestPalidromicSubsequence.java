package ALOGORITHM.leetcodeTop100;

/**
 * @author canoeYang
 * @Date 2020-08-06 11:27
 */

/**
 * 动态规划
 * 状态：dp[i][j] 表示s的第i个字符到第j个字符组成的子串中，最长的回文序列长度是多少
 * 转移方程如果第i个字符和第j个字符相同，dp[i][j] = dp[i+1][j-1]+2
 * 如果不相等：dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1])
 *
 */
public class LongestPalidromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        if(s.length()==0){
            return 0;
        }
        int len = s.length();
        int[][] dp = new int[len][len];
        for(int i = len ;i >=0;i--){
            dp[i][i] = 1;
            for(int j=i+1;j<len;j++){
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1]+2;
                }else{
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }
}
