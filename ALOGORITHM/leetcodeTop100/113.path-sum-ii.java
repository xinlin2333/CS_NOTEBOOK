/*
 * @lc app=leetcode id=113 lang=java
 *
 * [113] Path Sum II
 */

// @lc code=start

import java.util.ArrayList;
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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        traversal(root, targetSum, res, path);
        return res;
    }
    public void traversal(TreeNode root, List<List<Integer>> res, List<Integer> path, int targetSum) {
        path.add(root.val);
        if(root.left == null && root.right==null) {
            if(targetSum - root.val == 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        if(root.left!=null) {
            traversal(root.left, res, path, targetSum-root.val);
            path.remove(path.size()-1);
        }
        if(root.right != null) {
            traversal(root.right, res, path, targetSum-root.val);
            path.remove(path.size()-1);
        }
    }
}
// @lc code=end

