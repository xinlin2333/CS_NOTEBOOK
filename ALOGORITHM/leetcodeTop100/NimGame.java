package ALOGORITHM.leetcodeTop100;

/**
 * @author canoeYang
 * @Date 2020-08-10 11:26
 */

/**
 * 扔石头：每次可以取1-3块石头，由你先拿，问获胜的可能性大
 * 首先我要先获胜，那么对方拿的时候是4块石头，那这样不管怎么样，最后还剩1-3块石头
 * 所以怎么保证对方拿的时候是4块石头，那么在此之前的我应该取得石头为5-7块，这样保证最后只剩4块
 * 由此可推，看从开始拿石头的时候 ，是否为4的倍数，不为4的倍数，那么应该是我获胜
 */
public class NimGame {
    public boolean canWinNim(int n) {
        return n%4!=0;
    }
}
