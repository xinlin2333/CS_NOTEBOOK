package ALOGORITHM.JIANZHI;

/**
 * @author canoeYang
 * @Date 2020-08-30 19:37
 */
public class SubStructure26 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A==null || B==null){
            return false;
        }
        return isSub(A,B)||isSub(A.left,B)||isSub(A.right,B);
    }
    private boolean isSub(TreeNode a, TreeNode b){
        if(b==null){
            return true;
        }
        else if(a==null){
            return false;
        }
        return (a.val==b.val)&&isSub(a.left,b.left)&&isSub(a.right,b.right);
    }
}
class TreeNode{
    TreeNode left;
    TreeNode right;
    int val;
    TreeNode(int x){val=x;};
}
