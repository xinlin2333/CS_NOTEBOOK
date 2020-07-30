package ALOGORITHM.leetcodeTop100;

import java.util.HashMap;

public class pathSumIII {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val=x;}
    }
    public int pathSum(TreeNode root, int sum) {
        if(root == null){
            return 0;
        }
        // record the count of each sum
        HashMap<Integer,Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0,1);
        return pathSumHelper(root,prefixSumCount,sum,0);
    }
    public int pathSumHelper(TreeNode root,HashMap<Integer,Integer> prefixSumCount,int target,int curSum){
        if(root == null){
            return 0;
        }
        int res = 0;
        curSum += root.val;
        res += prefixSumCount.getOrDefault(curSum-target, 0);
        prefixSumCount.put(curSum, prefixSumCount.getOrDefault(curSum, 0)+1);
        res += pathSumHelper(root.left, prefixSumCount, target, curSum);
        res += pathSumHelper(root.right, prefixSumCount, target, curSum);

        prefixSumCount.put(curSum,prefixSumCount.get(curSum)-1);
        return res;
    }
    
}