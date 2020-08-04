package ALOGORITHM.公司真题;

import java.util.Scanner;

/**
 * @author canoeYang
 * @Date 2020-08-02 16:59
 */

/**
 *题目描述：
 * A 国的手机号码由且仅由 N 位十进制数字(0-9)组成。一个手机号码中有至少 K 位数字相同则被定义为靓号。A 国的手机号可以有前导零，比如 000123456 是一个合法的手机号。
 * 小多想花钱将自己的手机号码修改为一个靓号。修改号码中的一个数字需要花费的金额为新数字与旧数字之间的差值。比如将 1 修改为 6 或 6 修改为 1 都需要花 5 块钱。
 * 给出小多现在的手机号码，问将其修改成一个靓号，最少需要多少钱？
 * 输入描述：
 * 第一行包含2个整数 N、K，分别表示手机号码数字个数以及靓号至少有 K 个数字相同。
 * 第二行包含 N 个字符，每个字符都是一个数字('0'-'9')，数字之间没有任何其他空白符。表示小多的手机号码。
 * 数据范围：
 * 2 <= K <= N <= 10000
 * 输出描述：
 * 第一行包含一个整数，表示修改成一个靓号，最少需要的金额。
 * 第二行包含 N 个数字字符，表示最少花费修改的新手机号。若有多个靓号花费都最少，则输出字典序最小的靓号。
 */
public class PDD191 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int phoneNumSize = sc.nextInt();
        int repeatTimes = sc.nextInt();
        char[] phoneNum = sc.next().toCharArray();
        sc.close();
        minCost(phoneNumSize,repeatTimes,phoneNum);

    }
    public static void minCost(int phoneNumSize,int repeatTimes,char[] phoneNum){
        int minCost = Integer.MAX_VALUE;
        char[] newPhoneNum = new char[phoneNumSize];
        int[] repeat = getCount(phoneNum);
        for (int curNum = 0;curNum<10;curNum++){
            int restTimes = repeatTimes-repeat[curNum];
            if(restTimes<=0){
                minCost = 0;
                newPhoneNum = phoneNum;
                break;
            }
            int curNumCost = 0;
            int upperNum = curNum+1;
            int lowerNum = curNum-1;
            char[] changePhoneNum = new char[phoneNumSize];
            System.arraycopy(phoneNum,0,changePhoneNum,0,phoneNumSize);
            while(restTimes>0){
                if(upperNum<10){
                    for(int i=0;i<phoneNumSize && restTimes>0;i++){
                        if(phoneNum[i]-'0' == upperNum){
                            curNumCost += upperNum-curNum;
                            changePhoneNum[i] = (char)(curNum+'0');
                            restTimes--;
                        }
                    }
                }
                if(lowerNum>=0){
                    for(int i=phoneNumSize-1;i>=0&& restTimes>0;i--){
                        if(phoneNum[i]-'0'==lowerNum){
                            curNumCost+= curNum-lowerNum;
                            changePhoneNum[i] = (char) (curNum+'0');
                            restTimes--;
                        }
                    }
                }
                upperNum++;
                lowerNum--;
            }
            if(curNumCost<minCost){
                minCost = curNumCost;
                newPhoneNum = changePhoneNum;
            }
        }
        System.out.println(minCost);
        System.out.println(newPhoneNum);
    }
    public static int[] getCount(char[] phoneNum){
        int[] repeat = new int[10];
        for(int i=0;i<phoneNum.length;i++){
            repeat[phoneNum[i]-'0']++;
        }
        return repeat;
    }
}
