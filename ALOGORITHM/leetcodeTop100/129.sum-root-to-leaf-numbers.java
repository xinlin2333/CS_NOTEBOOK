/*
 * @lc app=leetcode id=129 lang=java
 *
 * [129] Sum Root to Leaf Numbers
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
    public int sumNumbers(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int res = 0;
        List<Integer> path = new ArrayList<>();
        traversal(root, path, root.val);
        for(int num : path) {
            res += num;
        }
        return res;
        
    }
    private static void traversal(TreeNode root, List<Integer> path, int num) {
        if(root.left != null) {
            int newNum = num*10 + root.left.val;
            traversal(root.left, path, newNum);
        }
        if(root.right != null) {
            int newNum = num * 10 + root.right.val;
            traversal(root.right, path, newNum);
        }
        if(root.left == null && root.right == null) {
            path.add(num);
        }
    }
}
// @lc code=end

