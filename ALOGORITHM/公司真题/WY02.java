package ALOGORITHM.公司真题;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author canoeYang
 * @Date 2020-08-08 14:46
 */
public class WY02 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] t = new int[m];
        for(int i=0;i<m;i++){
            t[i] = sc.nextInt();
        }
        func(n,m,t);
    }
    private static void func(int n, int m, int[] t){
        int[] res = new int[n];
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<m;i++){
            map.put(t[i],i);
        }
        for(int i=0;i<n;i++){
            if(map.containsKey(i)){
                res[i] = t[i];
            }else{
                res[i] = i;
            }
        }
        for(int i=0;i<n-1;i++){
            System.out.print(res[i]+"");
        }
        System.out.print(res[n-1]);
    }
}
