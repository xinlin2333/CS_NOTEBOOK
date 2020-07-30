package ALOGORITHM.leetcodeTop100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class QueueReconstructByHeight {
    public int[][] reconstructQueue(int[][] people) {
        if(people.length ==0){
            return new int[0][2];
        }
       
        Arrays.sort(people, new Comparator<int[]>(){

            @Override
            public int compare(int[] o1, int[] o2) {
                // TODO Auto-generated method stub
                return o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0];
            }
            
        });
        List<int[]> res= new ArrayList<>();
        for(int[] p: people){
            res.add(p[1], p);
        }
        return res.toArray(new int[people.length][2]);
    }
    
}