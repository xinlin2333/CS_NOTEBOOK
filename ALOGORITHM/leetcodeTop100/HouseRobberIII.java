package ALOGORITHM.leetcodeTop100;

import sun.reflect.generics.tree.Tree;

import java.util.HashMap;

/**
 * @author canoeYang
 * @Date 2020-08-05 8:04
 */
public class HouseRobberIII {
    // 暴力法（枚举）
    public int rob(TreeNode root) {
        if(root ==null){
            return 0;
        }
        int money = root.val;
        if(root.left!=null){
            money += rob(root.left.left)+rob(root.left.right);
        }
        if(root.right != null){
            money += rob(root.right.left) + rob(root.right.right);
        }
        return Math.max(money,rob(root.left)+rob(root.right));
    }
    // 空间换时间 利用hashmap来记忆化存储遍历的结果
    public int robWithMemo(TreeNode root){
        HashMap<TreeNode,Integer> memo = new HashMap<>();
        return robHelper(root,memo);
    }
    private int robHelper(TreeNode root,HashMap<TreeNode,Integer> memo){
        if(root ==null){
            return 0;
        }
        if(memo.containsKey(root)){
            return memo.get(root);
        }
        int money = root.val;
        if(root.left!=null){
            money += (robHelper(root.left.left,memo)+robHelper(root.left.right,memo));
        }
        if(root.right != null){
            money += (robHelper(root.right.left,memo) + robHelper(root.right.right,memo));
        }
        int res = Math.max(robHelper(root.left,memo)+robHelper(root.right,memo),money);
        memo.put(root,res);
        return res;
    }
    // 动态规划，对于每个节点的状态要么偷要么不偷；当前节点偷时，两个孩子节点不能偷；当不偷时，左右孩子的钱相加
    // root[0]= Math.max(rob(root.left)[0],rob(root.left)[1])+Math.max(rob(root.right)[0],rob(root.right)[1]
    // root[1] = rob(root.left)[0]+rob(root.right)[0]+root.val;
    public int robWithDp(TreeNode root){
        int[] dp = robWithDpHelper(root);
        return Math.max(dp[0],dp[1]);
    }
    private int[] robWithDpHelper(TreeNode root){
        if(root == null){
            return new int[2];
        }
        int[] dp = new int[2];
        int[] left = robWithDpHelper(root.left);
        int[] right = robWithDpHelper(root.right);
        dp[0] = Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        dp[1] = left[0] + right[0] + root.val;
        return dp;

    }


    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            x = val;
        }
    }
}
