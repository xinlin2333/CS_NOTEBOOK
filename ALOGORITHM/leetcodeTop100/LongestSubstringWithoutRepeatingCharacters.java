package ALOGORITHM.leetcodeTop100;

/**
 * @author canoeYang
 * @Date 2020-08-06 9:33
 */

import java.util.HashMap;

/**
 * 思路：滑动窗口思想；
 * 1、首先设定滑动窗口的长度为1，将右边的索引向右移动
 * 2、对比加入元素移动窗口内的子串是否重复
 * 3、否则，滑动窗口向右移动
 *
 * 使用两个指针，i,j最开始的时候i,j指向第一个元素，然后i往后移动，把扫描过的元素都放到map中
 * 如果扫描到的元素没有重复的就一直往后移，顺便记录一下最大值max，如果i扫描过的元素有重复的，就改边j的位置
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0){
            return 0;
        }
        int maxLen = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0,j=0;i<s.length();i++){
            if(map.containsKey(s.charAt(i))){
                j = Math.max(j,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            maxLen = Math.max(maxLen,i-j+1);
        }
        return maxLen;
    }
}
