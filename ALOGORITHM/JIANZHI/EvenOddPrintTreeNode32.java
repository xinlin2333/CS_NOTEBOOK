package ALOGORITHM.JIANZHI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author canoeYang
 * @Date 2020-08-30 21:09
 */
public class EvenOddPrintTreeNode32 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        while(queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<queue.size();i++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(level%2==1){
                    queue.add(node.right);
                    queue.add(node.left);
                }else{
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            res.add(list);
            list.remove(list.size()-1);
        }
        return res;

    }
}
