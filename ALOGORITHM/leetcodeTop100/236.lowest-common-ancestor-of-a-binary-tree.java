/*
 * @lc app=leetcode id=236 lang=java
 *
 * [236] Lowest Common Ancestor of a Binary Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root==q) {
            return root;
        }
        TreeNode l = lowestCommonAncestor(root.left, p,q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if (l!= null && r != null) {
            return root;
        }
        if(l!=null) {
            return l;
        }
        return r;
    }
}
// @lc code=end

