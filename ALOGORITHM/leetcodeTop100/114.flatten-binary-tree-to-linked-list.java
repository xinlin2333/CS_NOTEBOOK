/*
 * @lc app=leetcode id=114 lang=java
 *
 * [114] Flatten Binary Tree to Linked List
 */

// @lc code=start

import java.util.LinkedList;
import java.util.List;

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
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        LinkedList<TreeNode> tree = new LinkedList<TreeNode>();
        dfs(root,tree);
        TreeNode head = tree.removeFirst();
        head.left = null;
        while(tree.size()>0){
            TreeNode tmp = tree.removeFirst();
            tmp.left = null;
            head.right = tmp;
            head = head.right;
        }
    }
    public void dfs(TreeNode root, List<TreeNode> node) {
        if(root == null){
            return ;
        }
        node.add(root);
        dfs(root.left, node);
        dfs(root.right, node);
    }
}
// @lc code=end

