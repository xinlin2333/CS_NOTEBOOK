/*
 * @lc app=leetcode id=106 lang=java
 *
 * [106] Construct Binary Tree from Inorder and Postorder Traversal
 */

// @lc code=start

import javax.swing.tree.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length != postorder.length) {
            return null;
        }
        return construct(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }
    private TreeNode construct(int[] inorder,int inStart, int inEnd, int[] postorder, int postStart, int postEnd){
        if(inStart >= inEnd || postStart >= postEnd) {
            return null;
        }
        int rootValue = postorder[postEnd-1];
        int rootInIdx = 0;
        while(rootInIdx < inEnd && inorder[rootInIdx] != rootValue) {
            rootInIdx ++;
        }
        TreeNode root = new TreeNode(rootValue);
        int leftLen = rootInIdx-inStart;
        root.left = construct(inorder, inStart, rootInIdx, postorder, postStart, postStart+leftLen+1);
        root.right = construct(inorder, rootInIdx+1, inEnd, postorder, postStart+leftLen+1, postEnd);
        return root;
    }
}
// @lc code=end

