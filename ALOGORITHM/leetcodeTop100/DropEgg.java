package ALOGORITHM.leetcodeTop100;

/**
 * @author canoeYang
 * @Date 2020-08-09 22:30
 */

import java.util.Arrays;

/**
 * 1、楼层若为0，不管多少个鸡蛋都是0次
 * 2、楼层若为1，鸡蛋若为0个，0次，鸡蛋的个数大于1，则为1次
 * 3、鸡蛋若为0个，那么0次
 * 4、鸡蛋若为1个，那么楼层i为i次
 * 5、从楼层2楼开始考虑，若从i层扔鸡蛋，没有摔破的话,说明还可以往上看，摔破话，向下去考虑，并且鸡蛋的个数-1
 * dp[i][k]=Math.max(dp[i-1][k-1],dp[n-i][k])+1
 */
public class DropEgg {
    public int superEggDrop(int k, int n) {
        //dp[i][j]:一共有i层楼梯的情况下，使用j个鸡蛋的最少实验次数
        int[][] dp = new int[n+1][k+1];
        // 由于求最小值，因此初始化赋值为一个较大的数
        for(int i=0;i<=n;i++){
            Arrays.fill(dp[i],i);
        }
        //初始化：下标为0，1的行和下标为0、1的列
        //第0行：楼层为0的时候，不管鸡蛋个数多少，都测试不出鸡蛋的个数
        for(int j=0;j<=k;j++){
            dp[0][j]=0;
        }
        dp[1][0]=0;
        for(int j=1;j<=k;j++){
            dp[1][j]=1;
        }
        for(int i=0;i<=n;i++){
            dp[i][0]=0;
            dp[i][1]=i;
        }
        for(int i=2;i<=n;i++){
            for(int j=2;j<=k;j++){
                for(int h=1;h<=k;h++){
                    dp[i][j] = Math.min(dp[i][j],Math.max(dp[h-1][j-1],dp[i-h][j])+1);
                }
            }
        }
        return dp[n][k];


    }
}
