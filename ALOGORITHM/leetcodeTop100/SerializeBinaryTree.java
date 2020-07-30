package ALOGORITHM.leetcodeTop100;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SerializeBinaryTree {
     // Encodes a tree to a single string.
     public class TreeNode{
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x){val =x;}
     }
     public String serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        preOrder(root,sb);
        return sb.substring(0,sb.length()-1);
        
    }
    public void preOrder(TreeNode root,StringBuilder sb){
        if(root == null){
            sb.append("#,");
            return ;
        }
        sb.append(root.val);
        sb.append(",");
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length()==0){
            return null;
        }
        String[] strs = data.split(",");
        List<String> list = new LinkedList<>(Arrays.asList(strs));
        return buildTree(list);
    }
    public TreeNode buildTree(List<String> list){
        if(list.get(0).equals("#")){
            list.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        root.left = buildTree(list);
        root.right = buildTree(list);
        return root;
    }
    
}