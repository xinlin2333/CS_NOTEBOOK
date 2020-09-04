package ALOGORITHM.公司真题;

import java.util.Scanner;

/**
 * @author canoeYang
 * @Date 2020-08-08 14:45
 */
public class WY01 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i=0;i<n;i++){
            a[i] = sc.nextInt();
        }
        func(a,n);
    }
    private static void func(int[] a,int n){
        int count =0;
        for(int i=0;i<n;i++){
            if(a[i]==2 || a[i]==3){
                count++;
            }else if(a[i]>3){
                int num = a[i]/2;
                count+=num;
            }

        }
        System.out.println(count);
    }

}
