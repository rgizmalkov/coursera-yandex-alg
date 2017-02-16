package AlgorithmicToolbox.Week_02;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by elion on 15.02.2017.
 */
public class LeastCommonMultiple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger val1 = sc.nextBigInteger();
        BigInteger val2 = sc.nextBigInteger();
        BigInteger res = leastCommonMultiple(val1, val2);
        System.out.println(res);
    }

    public static BigInteger leastCommonMultiple(BigInteger a, BigInteger b) {
        BigInteger d = EuclideanGCD(a, b);
        return a.multiply(b.divide(d));
    }


    private static BigInteger EuclideanGCD(BigInteger val1, BigInteger val2) {
        if(BigInteger.ZERO.equals(val2)){
            return val1;
        }else {
            return EuclideanGCD(val2, val1.remainder(val2));
        }
    }
}
