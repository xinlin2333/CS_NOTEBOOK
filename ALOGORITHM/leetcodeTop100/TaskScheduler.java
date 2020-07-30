package ALOGORITHM.leetcodeTop100;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        if(tasks.length<=1 && n<1){
            return tasks.length;
        }
        int[] count = new int[26];
        for(char ch:tasks){
            count[ch-'A']++;
        }
        int maxCount = count[25];
        int maxTimes = (maxCount -1)*(n+1)+1;
        int i=24;
        while(i>=0 && count[i]==maxCount){
            maxTimes++;
            i--;
        }
        return Math.max(maxTimes,tasks.length);
    }
    
}