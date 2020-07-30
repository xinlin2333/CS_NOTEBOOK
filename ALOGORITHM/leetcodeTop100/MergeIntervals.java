package ALOGORITHM.leetcodeTop100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if(intervals.length==0){
            return new int[0][2];
        }
        Arrays.sort(intervals,new Comparator<int[]>(){

            @Override
            public int compare(int[] o1,int[] o2) {
                // TODO Auto-generated method stub
                return o1[0]-o2[0];
            }
            
        });
        List<int[]> res = new ArrayList<>();
        int i=0;
        int len = intervals.length;
        while(i<len){
            int left = intervals[i][0];
            int right = intervals[i][1];
            while(i<intervals.length-1 && right>intervals[i+1][0]){
                right = Math.max(right,intervals[i+1][1]);
                i++;
            }
            res.add(new int[]{left,right});
            i++;
        }
        return res.toArray(new int[intervals.length][2]);
    }
}