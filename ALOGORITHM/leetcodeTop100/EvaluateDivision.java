package ALOGORITHM.leetcodeTop100;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 思路：构造邻接矩阵，记录每对pair的值
 * 1、构造图的前提是图的每个节点代表的是什么，这里需要将在equations中出现的字母进行序号标记；利用hashmap
 * 2、构造二维矩阵，遍历equations，将已知值放入到矩阵中那
 * 3、利用floyd算法将存在联系的间接性计算加入到图中
 * 4、根据给的查询list 进行一一遍历，看pair的每个字符是否在图中
 */
public class EvaluateDivision{

    public double[] calcEquation(List<List<String>> equations,double[] values,List<List<String>> queries){
        Map<String,Integer> map = new HashMap<>();
        int count = 0;
        // 统计出现的字符个数，这样可以得到矩阵的长度
        for(List<String> list:equations){
            for(String s:list){
                if(!map.containsKey(s)){
                    map.put(s,count++);
                }
            }
        }
        //构建矩阵
        double[][] graph = new double[count+1][count+1];
        for(String s:map.keySet()){
            int x = map.get(s);
            graph[x][x] = 1.0;

        }
        int idx = 0;
        for(List<String> list:equations){
            String a = list.get(0);
            String b = list.get(1);
            int aa = map.get(a);
            int bb = map.get(b);
            double value = values[idx++];
            graph[aa][bb] = value;
            graph[bb][aa] = 1/value;
        }
        int n = count+1;
        for(int i=0;i<n;i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j == k || graph[j][k] != 0) {
                        continue;
                    }
                    if (graph[i][j] != 0 && graph[i][k] != 0) {
                        graph[j][k] = graph[j][i] * graph[i][k];
                    }
                }
            }
        }
        double[] res = new double[queries.size()];
        for(int i=0;i<res.length;i++){
            List<String> q = queries.get(i);
            String a = q.get(0);
            String b = q.get(1);
            if(map.containsKey(a) && map.containsKey(b)){
                double ans = graph[map.get(a)][map.get(b)];
                res[i] = (ans==0?-1.0:ans);
            }else{
                res[i]= -1.0;
            }
        }
        return res;

    }
}