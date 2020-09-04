package ALOGORITHM.公司真题;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author canoeYang
 * @Date 2020-08-06 19:00
 */
public class JD03 {
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            func(n);
        }
        private static void func(int n){
            double sum  = 0;
            for(int i=1;i<=n+1;i++){
                if(i%2==0){
                    sum+= (-(1.0/i));
                }else{
                    sum += 1.0/i;
                }

            }
            BigDecimal res = new BigDecimal(sum*0.2).setScale(4,BigDecimal.ROUND_HALF_UP);
            System.out.print(res);
        }
}
