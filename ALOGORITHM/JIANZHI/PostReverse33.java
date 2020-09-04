package ALOGORITHM.JIANZHI;

/**
 * @author canoeYang
 * @Date 2020-08-30 21:27
 */

/**
 * 思路：二叉树中根大于左边节点小于右边节点，后续遍历最后一个为根节点，然后从最左边开始遍历找到第一个比根大的去判断右边的节点是否都比根打
 * 然后递归处理左边和右边
 */
public class PostReverse33 {
    public boolean verifyPostorder(int[] postorder) {
        if(postorder.length<2){
            return true;
        }
        return verify(postorder,0,postorder.length-1);

    }
    private boolean verify(int[] postorder,int left,int right){
        if(left>right)
            return true;
        int rootVal = postorder[right];
        int pos = left;
        while(pos<right && postorder[pos]<rootVal){
            pos++;
        }
        for(int i = pos+1;i<right;i++){
            if(postorder[i]<rootVal){
                return false;
            }
        }
        if(!verify(postorder,left,pos-1))
            return false;
        if(!verify(postorder,pos,right-1))
            return false;
        return true;
    }
}
