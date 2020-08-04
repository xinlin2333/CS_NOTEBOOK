package ALOGORITHM.公司真题;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author canoeYang
 * @Date 2020-08-02 18:30
 */
public class PDD193 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i=1;i<=n;i++){
            a[i] = sc.nextInt();
        }
        Arrays.sort(a);
        int i=1;
        int j = n;
        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;
        while(i<j){
            maxSum = Math.max(maxSum,a[i]+a[j]);
            minSum = Math.max(minSum,a[i]+a[j]);
            i++;
            j--;
        }
        System.out.println(maxSum-minSum);

    }
}
