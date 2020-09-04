package ALOGORITHM.JIANZHI;

import java.util.Scanner;

/**
 * @author canoeYang
 * @Date 2020-08-30 16:15
 */
public class ReverseStr58 {
    public static String reverseLeftWords(String s, int n) {
        if(s.length()==0){
            return s;
        }
        if(n>s.length()){
            return "";
        }
        char[] chs = s.toCharArray();
        reverse(chs,0,chs.length-1);
        reverse(chs,0,n-1);
        reverse(chs,n,s.length()-1);
        return String.valueOf(chs);
    }
    private static void reverse(char[] chs,int low,int high){

        while(low<high){
            swap(chs,low,high);
            low++;
            high--;
        }
    }
    private static void swap(char[] ch,int a,int b){
        char tmp = ch[a];
        ch[a] = ch[b];
        ch[b] = tmp;

    }
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();
        String res = reverseLeftWords(str,n);
        System.out.println(res);
    }
}
