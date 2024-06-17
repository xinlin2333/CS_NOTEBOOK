/*
 * @lc app=leetcode id=105 lang=java
 *
 * [105] Construct Binary Tree from Preorder and Inorder Traversal
 */

// @lc code=start
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length) {
            return null;
        } 
        return construct(preorder, 0, preorder.length, inorder, 0, inorder.length);
        
    }
    private TreeNode construct(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart >= preEnd || inStart >= inEnd) {
            return null;
        }
        int rootValue = preorder[preStart];
        int rootInIdx = inStart;
        while(rootInIdx < inEnd ) {
            if(inorder[rootInIdx] == rootValue) {
                break;
            }
            rootInIdx++;
        }
        TreeNode root = new TreeNode(rootValue);
        int leftsize = rootInIdx - preStart;
        root.left = construct(preorder, preStart+1, preStart+leftsize+1, inorder, inStart, rootInIdx);
        root.right = construct(preorder, preStart+leftsize+1, preEnd, inorder, rootInIdx+1, inEnd);
        return root;
    }
   
}
// @lc code=end

