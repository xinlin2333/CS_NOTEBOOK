package ALOGORITHM.JIANZHI;

import java.util.Scanner;

/**
 * @author canoeYang
 * @Date 2020-09-01 19:46
 */
public class pdd04 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] special = new int[m];
        for(int i=0;i<m;i++){
            special[i] = sc.nextInt();
        }
        count(n,special,m);
    }
    public static void count(int n, int[] special,int m){

    }
}
