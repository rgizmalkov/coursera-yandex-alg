package AlgorithmicToolbox.Week_02;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by romanizmalkov on 14.02.17.
 */
public class Fibonacci {
    public static void main(String[] args) {
//        long l1 = System.currentTimeMillis();
//        Scanner sc = new Scanner(System.in);
//        System.out.println(lastDigitOfALargeFibonacciNumber(sc.nextInt()));
//        long l2 = System.currentTimeMillis();
//        System.out.println(l2-l1);
        System.out.println(FibList(3));
        System.out.println(FibList(9));
//        for (int i = 0; i < 121; i++) {
//            System.out.printf("%d, ", FibList(i).remainder(BigInteger.valueOf(10)));
//            System.out.println(FibList(i).remainder(BigInteger.valueOf(4)));
//        }
    }

    public static int FibRecourse(int n) {
        if (n<=1){
            return n;
        }else {
            return FibRecourse(n-1) + FibRecourse(n-2);
        }
    }

    public static BigInteger FibList(int n) {
        if(n <= 1) return BigInteger.valueOf(n);
        BigInteger[] F = new BigInteger[n+1];
        F[0] = BigInteger.valueOf(0);
        F[1] = BigInteger.valueOf(1);
        for (int i = 2; i < n+1; i++) {
            F[i] = F[i - 1].add(F[i - 2]);
        }
        return F[n];
    }

    public static int lastDigitOfALargeFibonacciNumber(int n){
        if(n <= 1) return n;
        int f0 = 0;
        int f1 = 1;
        int f2 = 0;

        for (int i = 2; i < n + 1; i++) {
            f2 = mod(f0, f1);
            f0 = f1;
            f1 = f2;
        }
        return f2;
    }

    private static int mod(int a, int b) {
        return (a+b)%10;
    }
}
