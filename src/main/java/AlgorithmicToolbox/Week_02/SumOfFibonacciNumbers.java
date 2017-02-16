//package AlgorithmicToolbox.Week_02;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by elion on 16.02.2017.
 */
public class SumOfFibonacciNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        System.out.println(val(lastDigitOfSummFibonacciNumbers(new Scanner(System.in).nextBigInteger())));
        System.out.println(lastDigitOfPartitialSumFibonacciNumbers(sc.nextBigInteger(), sc.nextBigInteger()));
    }

    private static int val(int val) {
        return val == - 1 ? 9 : val;
    }

    private static int lastDigitOfSummFibonacciNumbers(BigInteger n){
        if(BigInteger.valueOf(2).compareTo(n) >= 0) return n.intValue();
        int f0 = 0;
        int f1 = 1;
        int f2 = 0;
        n = (BigInteger.valueOf(2)).add(n);
        int d = n.remainder(BigInteger.valueOf(60 /*period of mod 10*/)).intValue();
        if (d <= 1) return d;
        for (int i = 2; i < d + 1; i++) {
            f2 = (f1 + f0)%10;
            f0 = f1;
            f1 = f2;
        }
        return f2 - 1;
    }

    private static int lastDigitOfPartitialSumFibonacciNumbers(BigInteger a, BigInteger b){
        if(a.compareTo(b) == 0) val(lastDigitOfSummFibonacciNumbers(a));
        int val1 = val(lastDigitOfSummFibonacciNumbers(a.subtract(BigInteger.ONE)));
        int val2 = val(lastDigitOfSummFibonacciNumbers(b));
        return ret(val2 - val1);
    }

    private static int ret(int val) {
        return val < 0 ? 10 + val: val;
    }
}
