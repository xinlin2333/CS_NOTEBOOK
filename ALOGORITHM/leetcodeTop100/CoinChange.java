package ALOGORITHM.leetcodeTop100;

/**
 * @author canoeYang
 * @Date 2020-08-09 18:31
 */

/**
 * 零钱兑换
 * 1、基本状态：dp[0]=0;
 * 2、状态转换：dp[i]=Math.min(dp[i],dp[i-coin]+1)(i-coin>=0);
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if(amount==0){
            return 0;
        }
        int[] dp = new int[amount+1];
        dp[0]=0;
        for(int i=1;i<=amount;i++){
            int min = Integer.MAX_VALUE;
            for(int coin:coins){
                if(i-coin>=0 && dp[i-coin]<min){
                    min = dp[i-coin]+1;
                }
            }
            dp[i] = min;
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }
}
