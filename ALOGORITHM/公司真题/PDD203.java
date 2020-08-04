package ALOGORITHM.公司真题;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author canoeYang
 * @Date 2020-08-02 18:51
 */
public class PDD203 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int t = sc.nextInt();
        int[][] lunch = new int[n][2];
        int[][] dinner = new int[m][2];
        for(int i=0;i<n;i++){
            lunch[i][0] = sc.nextInt();
            lunch[i][1] = sc.nextInt();
        }
        for(int j=0;j<m;j++){
            dinner[j][0] = sc.nextInt();
            dinner[j][1] = sc.nextInt();
        }
        if(t==0){
            System.out.print(0);
        }else{
            func(n,m,t,lunch,dinner);
        }
    }
    public static void func(int n, int m,int t,int[][] lunch,int[][] dinner){
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<n;i++){
            if(lunch[i][1]>=t){

                if(map.containsKey(lunch[i][1])){
                    if(map.get(lunch[i][1])>lunch[i][0]){
                        map.put(lunch[i][1],lunch[i][0]);
                    }
                }else{
                    map.put(lunch[i][1],lunch[i][0]);
                }
            }else{
                for(int j=0;j<m;j++){
                    if(dinner[j][1]>=t){

                        if(map.containsKey(dinner[j][1])){
                            if(map.get(dinner[j][1])>dinner[j][0]){
                                map.put(dinner[j][1],dinner[j][0]);
                            }
                        }
                    }else {
                        int cur = dinner[j][1] + lunch[i][1];
                        if (cur >= t) {
                            int sum = dinner[j][0] + lunch[i][0];
                            if (map.containsKey(cur)) {
                                if (map.get(cur) > sum) {
                                    map.put(cur, sum);
                                }
                            } else {
                                map.put(cur, sum);
                            }
                        }
                    }
                }
            }
        }
        int minCost = Integer.MAX_VALUE;
        if(map.size()==0){
            minCost = -1;
        }else {
            for (Map.Entry entry : map.entrySet()) {
                minCost = Math.min(minCost, (Integer) entry.getValue());
            }
        }
        System.out.print(minCost);
    }
}
