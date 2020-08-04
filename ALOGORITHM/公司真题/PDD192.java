package ALOGORITHM.公司真题;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author canoeYang
 * @Date 2020-08-02 17:55
 */
public class PDD192 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] tree = new int[n+1];
        int treeSum = 0;
        for(int i=1;i<=n;i++){
            tree[i] = sc.nextInt();
            treeSum += tree[i];
        }
        sc.close();
        List<String> res = new ArrayList<>();
        if(dfs(0,tree,n,res,treeSum)){
            System.out.println(res);
        }else{
            System.out.println("-");
        }

    }
    private static boolean dfs(int idx,int[] tree,int treeSize,List<String> res, int treeSum){
        if(idx== treeSum){
            return true;
        }
        int leftSpace = treeSum-idx;
        if(!checkSpace(treeSize,tree,leftSpace)){
            return false;
        }
        for(int i=1;i<treeSize;i++){
            if(idx == 0 || tree[i]!=0 && i!=Integer.valueOf(res.get(i-1))){
                tree[i]--;
            }
            res.add(i+"");
            if(dfs(idx+1,tree,treeSize,res,treeSum)){
                return true;
            }
            tree[i]++;
            res.remove(res.size()-1);
        }
        return false;

    }
    private static boolean checkSpace(int treeSize,int[] tree,int leftSpace ){
        for(int i=1;i<=treeSize;i++){
            if(tree[i]>(leftSpace+1)/2){
                return false;
            }
        }
        return true;
    }
}
