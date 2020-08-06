package ALOGORITHM.leetcodeTop100;

/**
 * @author canoeYang
 * @Date 2020-08-06 10:22
 */

/**
 * 1、中心扩散法
 * 在长度为N的字符串中，可能的回文串中心位置有2n-1;
 * 从某个中心回文串开始统计回文串个数[a,b]为回文串，那么[a+1,b-1]肯定是回文串
 */
public class PalindromicSubStrings {
    public int countSubstrings(String s) {
        if(s.length() == 0){
            return 0;
        }
        int len = s.length();
        int maxLen =0;
        for(int center = 0;center<2*len-1;center++){
            int left = center/2;
            int right = left +center%2;
            while(left>=0 && right<len && s.charAt(left)==s.charAt(right)){
                maxLen ++;
                left--;
                right++;
            }
        }
        return maxLen;
    }
}
