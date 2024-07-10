/*
 * @lc app=leetcode id=173 lang=java
 *
 * [173] Binary Search Tree Iterator
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.Deque;

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
class BSTIterator {
    Deque<Integer> q = new ArrayDeque();

    public BSTIterator(TreeNode root) {
        dfsTree(root);
    }

    public void dfsTree(TreeNode root) {
        while(root!=null) {
            q.addLast(root);
            root = root.left;
        }
    }
    
    public int next() {
        TreeNode node = q.pollLast();
        node = node.right;
        dfsTree(node);
        return node.val;

    }
    
    public boolean hasNext() {
        return !q.isEmpty();
        
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
// @lc code=end

