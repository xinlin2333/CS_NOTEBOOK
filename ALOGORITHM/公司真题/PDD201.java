package ALOGORITHM.公司真题;

import java.util.Scanner;

/**
 * @author canoeYang
 * @Date 2020-08-02 18:50
 */
public class PDD201 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        int[] d = new int[n];
        for(int i = 0; i < n; i++){
            d[i] = sc.nextInt();
        }
        func(k,n,d);
    }
    public static void func(int k,int n, int[] d){
        int sum = 0;
        int backNum = 0;
        boolean flag = false;
        for(int i=0;i<n;i++){
            sum += d[i];
            if(sum==k){
                flag = true;
                break;
            }else if(sum>k){
                sum = k- (sum-k);
                backNum++;
            }
        }
        if(flag){
            System.out.println("paradox");
        }else{
            System.out.println(Math.abs(k-sum));
            System.out.println(backNum);
        }
    }
}
