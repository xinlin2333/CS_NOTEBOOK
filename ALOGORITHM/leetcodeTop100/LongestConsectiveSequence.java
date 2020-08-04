package ALOGORITHM.leetcodeTop100;

import java.util.HashSet;
import java.util.Set;

/**
 * @author canoeYang
 * @Date 2020-08-04 8:26
 */

/**
 * 思路：无序数组中找出最长的连续子序列，首先因为是连续的，所以可以考虑每遍历一个数组元素，看他的下一个是否在集合中，如果在的话，继续遍历
 * 1、设置一个set集合（去重）
 * 2、遍历set 看是否存在连续子序列
 * 时间复杂度：O(N) 空间复杂度：O(N)
 */
public class LongestConsectiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums ==null || nums.length ==0){
            return 0;
        }
        Set<Integer> numSet = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            numSet.add(nums[i]);
        }
        int longestSeq = 0;
        for(int num:numSet){
            if(!numSet.contains(num-1)){
                int currentNum = num;
                int curLongestSeq =1;
                while(numSet.contains(currentNum+1)){
                    currentNum +=1;
                    curLongestSeq +=1;
                }
                longestSeq = Math.max(longestSeq,curLongestSeq);
            }
        }
        return longestSeq;
    }
}
