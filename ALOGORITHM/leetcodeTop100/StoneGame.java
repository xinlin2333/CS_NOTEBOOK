package ALOGORITHM.leetcodeTop100;

/**
 * @author canoeYang
 * @Date 2020-08-10 11:56
 */
public class StoneGame {
    public boolean stoneGame(int[] piles) {
        int len = piles.length;
        Pair[][] dp = new Pair[len][len];
        for(int i=0;i<len;i++){
            for(int j=i;j<len;j++){
                dp[i][j] = new Pair(0,0);
            }
        }
        for(int i=0;i<len;i++){
            dp[i][i].first = piles[i];
            dp[i][i].second = 0;
        }
        for(int l=2;l<=len;l++){
            for(int i=0;i<=len-l;i++){
                int j = l+i-1;
                int left = piles[i] + dp[i+1][j].second;
                int right = piles[j] = dp[i][j-1].second;
                if(left > right){
                    dp[i][j].first = left;
                    dp[i][j].second = dp[i+1][j].first;
                }else{
                    dp[i][j].first = right;
                    dp[i][j].second = dp[i][j-1].first;
                }

            }
        }
        Pair res= dp[0][len-1];
        return (res.first-res.second)>0;
    }

}
class Pair{
    int first;
    int second;
    public Pair(int first,int second){
        this.first = first;
        this.second = second;
    }
}
