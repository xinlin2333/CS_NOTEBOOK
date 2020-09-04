package ALOGORITHM.JIANZHI;

import java.util.Scanner;

/**
 * @author canoeYang
 * @Date 2020-09-01 19:41
 */
public class PDD03 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] matrix = new int[n][2];
        for(int i=0;i<n;i++){
                matrix[i][0] = sc.nextInt();
                matrix[i][1] = sc.nextInt();
        }
        ks(matrix,n,m);
    }
    public static void ks(int[][] matrix,int n,int m){
        if(n==0 || m==0){
            System.out.print(-1);
        }

    }
}
