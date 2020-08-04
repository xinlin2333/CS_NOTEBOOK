package ALOGORITHM.leetcodeTop100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 思路：首先遇到
 */
//class Solution {
//    public String decodeString(String s){
//        if(s==null || s.length()==0){
//            return "";
//        }
//        StringBuilder sb = new StringBuilder();
//        Deque<Integer> mutiNum = new ArrayDeque<>();
//        Deque<String> res = new ArrayDeque<>();
//        int muti = 0;
//        for(char c:s.toCharArray()){
//            if(c=='['){
//                mutiNum.addLast(muti);
//                res.addLast(sb.toString());
//                muti = 0;
//                sb = new StringBuilder();
//            }else if(c ==']'){
//                StringBuilder tmp = new StringBuilder();
//                int curMuti = mutiNum.removeLast();
//                for(int i=0;i<curMuti;i++){
//                    tmp.append(sb);
//                }
//                sb = new StringBuilder(res.removeLast()+tmp);
//            }else if(c>='0'&&c<='9'){
//                muti = muti*10 + Integer.parseInt(c+"");
//            }else{
//                sb.append(c);
//            }
//        }
//        return sb.toString();
//    }
//}