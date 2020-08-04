package ALOGORITHM.leetcodeTop100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 思路：拓扑排序，由于课程之间有前提依赖性，即到达某个点必须得有其他相依赖的点先到
 * 1、构建邻接表List<Integer>[] graphic
 * 2、根据graphic统计每个点的入度
 * 3、将入度为0的入队
 * 4、出队并排查其相邻的点是否入度为0
 * 5、最后看队列是否为空来判断是否能上完课
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses ==0 || prerequisites.length==0){
            return true;
        }
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for(int i=0;i<numCourses;i++){
            graph.add(new ArrayList<>());
        }
        for(int[] pre:prerequisites){
            indegree[pre[0]]++;
            graph.get(pre[1]).add(pre[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(indegree[i]==0){
                queue.offer(indegree[i]);
            }
        }
        while(!queue.isEmpty()){
            int course = queue.poll();
            numCourses--;
            for(int cur:graph.get(course)){
                if(--indegree[cur]==0){
                    queue.offer(cur);
                }
            }
        }
        return numCourses==0;
    }
    
}