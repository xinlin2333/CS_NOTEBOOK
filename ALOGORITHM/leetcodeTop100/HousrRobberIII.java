package ALOGORITHM.leetcodeTop100;

class Solution {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val=x;}
    }
    public int rob(TreeNode root) {
        if(root == null){
            return 0;
        }
        int money = root.val;
        if(root.left!=null){
            money+=rob(root.left.left)+rob(root.left.right);
        }
        if(root.right !=null){
            money+=rob(root.right.left) + rob(root.right.right);
        }
        return Math.max(money,rob(root.left)+rob(root.right));
        
    }
}