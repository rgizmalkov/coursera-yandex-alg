//package AlgorithmicToolbox.Week_02;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by elion on 14.02.2017.
 */
public class GreatestCommonDivisor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        System.out.println(PrimeGCD(100, 200));
        System.out.println(EuclideanGCD(sc.nextBigInteger(), sc.nextBigInteger()));
    }


    public static long PrimeGCD(long val1, long val2){
        long gcd = 0;
        long top = Math.max(val1, val2);
        for (long d = 1; d < top; d++) {
            if(val1%d == 0 && val2%d==0){
                gcd = d;
            }
        }
        return gcd;
    }

    public static BigInteger EuclideanGCD(BigInteger val1, BigInteger val2) {
        if(BigInteger.ZERO.equals(val2)){
            return val1;
        }else {
            return EuclideanGCD(val2, val1.remainder(val2));
        }
    }
}
