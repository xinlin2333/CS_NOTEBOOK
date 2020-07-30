package ALOGORITHM.leetcodeTop100;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TopKElement {
    public int[] topKFrequent(int[] nums, int k) {
        if(nums.length ==0){
            return new int[0];
        }
        int len = nums.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num:nums){
            map.put(num,map.getOrDefault(num, 0)+1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                // TODO Auto-generated method stub
                return map.get(o1)-map.get(o2);
            }
        });
        for(int key:map.keySet()){
            queue.offer(key);
            if(queue.size()>=k){
                queue.poll();
            }
        }
        int count = 0;
        int[] res = new int[len];
        while(!queue.isEmpty()){
            res[count++] = queue.poll();
        }
        return res;
    }
    
}