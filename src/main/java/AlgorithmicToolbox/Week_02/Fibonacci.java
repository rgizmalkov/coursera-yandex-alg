package AlgorithmicToolbox.Week_02;

import java.math.BigInteger;

/**
 * Created by romanizmalkov on 14.02.17.
 */
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(FibList(100));
    }

    public static int FibRecours(int n) {
        if (n<=1){
            return n;
        }else {
            return FibRecours(n-1) + FibRecours(n-2);
        }
    }

    public static BigInteger FibList(int n) {
        BigInteger[] F = new BigInteger[n];
        F[0] = BigInteger.valueOf(0);
        F[1] = BigInteger.valueOf(1);
        for (int i = 2; i < n; i++) {
            F[i] = F[i - 1].add(F[i - 2]);
        }
        return F[n-1];
    }
}
