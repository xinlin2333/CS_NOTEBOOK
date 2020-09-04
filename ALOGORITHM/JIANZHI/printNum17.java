package ALOGORITHM.JIANZHI;

import java.util.ArrayList;
import java.util.List;

/**
 * @author canoeYang
 * @Date 2020-08-30 16:48
 */
public class printNum17 {
    public int[] printNumbers(int n) {
        int lastNum = 0;
        for(int i=1;i<=n;i++){
            lastNum = lastNum*10+9;
        }
        List<Integer> res = new ArrayList<>();
        int i=1;
        while(i<=lastNum){
            res.add(i);
            i++;
        }
        return res.stream().mapToInt(x->x).toArray();
    }
    // improve

}
