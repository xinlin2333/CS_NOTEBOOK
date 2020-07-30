package ALOGORITHM.leetcodeTop100;

import java.util.ArrayList;
import java.util.List;

public class Subset {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        if(nums.length ==0){
            return res;
        }
        dfs(nums,0,new ArrayList<>());
        return res;
    }
    public void dfs(int[] nums,int idx, List<Integer> list){
        if(idx>nums.length){
            return;
        }
        if(!res.contains(list)){
            res.add(list);
        }
        for(int i=idx;i<nums.length;i++){
            list.add(nums[i]);
            dfs(nums,i+1,list);
            list.remove(list.size()-1);
        }
    }
    
}