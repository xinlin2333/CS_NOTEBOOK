package ALOGORITHM.公司真题;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author canoeYang
 * @Date 2020-08-06 18:59
 */
public class JD02 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        func(n,m);
    }

    public  static void func(int n,int m){
        int count =0;
        for(int i=n;i<=m;i++){
            if(i<10){
                if(i == 1 || i==2 || i==3|| i==5 || i==7){
                    count++;
                }
            }else{
                if(judge(String.valueOf(i))){
                    count++;
                }
            }
        }
        System.out.println(count);
    }
    public static boolean judge(String s){
        char[] ch = s.toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        int pos = 0;
        for(int i=0;i<s.length();i++){
            map.put(ch[i],map.getOrDefault(ch[i],0)+1);
            if(map.get(ch[i])==1){
                pos = i;
            }
        }
        if(map.size()>2){
            return false;
        }
        String tmp = s.substring(0,pos)+s.substring(pos+1);
        if(Integer.valueOf(tmp)==0){
            return false;
        }
        if(isPrime(tmp)){
            return true;
        }

        return false;
    }

    private static boolean isPrime(String tmp){
        int num = Integer.valueOf(tmp);
        if(num==1 || num==2){
            return true;
        }
        for(int i=2;i*i<num;i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }

}
