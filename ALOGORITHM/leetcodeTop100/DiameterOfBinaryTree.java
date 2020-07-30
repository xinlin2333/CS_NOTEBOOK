package ALOGORITHM.leetcodeTop100;

public class DiameterOfBinaryTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val=x;}
    }
    int maxDiameter =0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
        depth(root);
        return maxDiameter;
    }
    public int depth(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        maxDiameter = Math.max(left+right,maxDiameter);
        return Math.max(left,right)+1;
    }
    
}