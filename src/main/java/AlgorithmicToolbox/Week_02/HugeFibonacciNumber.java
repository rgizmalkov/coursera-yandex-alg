package AlgorithmicToolbox.Week_02;

import javax.annotation.PostConstruct;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by elion on 15.02.2017.
 */
public class HugeFibonacciNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger n = sc.nextBigInteger();
        BigInteger mod = sc.nextBigInteger();
        System.out.println(alg2(n, mod));
//        System.out.println(alg(n, mod));
    }

    private static int alg2(BigInteger n, BigInteger mod) {
        BigInteger period = rules(mod);
        if(!period.equals(BigInteger.valueOf(-1)))
            return val(n.remainder(period).intValue(), mod);

        List<BigInteger> fib = new ArrayList<>();
        fib.add(BigInteger.ZERO); fib.add(BigInteger.ONE);
        int p = 0;

        for (BigInteger i = BigInteger.valueOf(2); true;) {
            fib.add(fib.get(i.subtract(BigInteger.ONE).intValue()).add(fib.get(i.subtract(BigInteger.valueOf(2)).intValue())).remainder(mod));
            p++;
            if (fib.get(i.intValue()).equals(BigInteger.ONE) && fib.get(i.subtract(BigInteger.ONE).intValue()).equals(BigInteger.ZERO)) {
                break;
            }
            i = i.add(BigInteger.ONE);
        }
        System.out.println(p);
        return fib.get(n.remainder(BigInteger.valueOf(p)).intValue()).intValue();
    }

    private static int val(int n, BigInteger mod) {
        BigInteger f0 = BigInteger.ZERO;
        BigInteger f1 = BigInteger.ONE;
        BigInteger f2 = null;
        for (int i = 2; i < n+1; i++) {
            f2 = (f1.add(f0)).remainder(mod);
            f0 = f1;
            f1 = f2;
        }
        return f2.intValue();
    }

    private static int alg1(BigInteger n, BigInteger mod) {
        int period = 0;
        List<BigInteger> mods = new ArrayList<>();
        mods.add(BigInteger.ZERO); mods.add(BigInteger.ONE);
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(n) < 1;) {
            mods.add(mods.get(i.subtract(BigInteger.ONE).intValue()).add(mods.get(i.subtract(BigInteger.valueOf(2)).intValue())).remainder(mod));
            period++;
            if (mods.get(i.intValue()).equals(BigInteger.ONE) && mods.get(i.subtract(BigInteger.ONE).intValue()).equals(BigInteger.ZERO)) {
                break;
            }
            i = i.add(BigInteger.ONE);
        }
        System.out.println("Period alg1: " + period);
        return mods.get(n.remainder(BigInteger.valueOf(period)).intValue()).intValue();
    }

    public static int alg(BigInteger n, BigInteger mod) {
        BigInteger period1 = rules(mod);
        System.out.println(!period1.equals(BigInteger.valueOf(-1)) ? "Period alg: " + period1 : "");
        if(!period1.equals(BigInteger.valueOf(-1)))
            return FibList(n.remainder(period1).intValue()).remainder(mod).intValue();
        int period = 0;
        List<BigInteger> fmod = new ArrayList<>();
        fmod.add(BigInteger.ZERO);
        fmod.add(BigInteger.ONE);

        int fibNum = 2;
        BigInteger val = null;
        BigInteger val2 = BigInteger.ZERO;
        BigInteger val1 = BigInteger.ONE;
        BigInteger dec = null;
        while (true){
            val = val1.add(val2);
            dec = val.remainder(mod);
            if (BigInteger.ZERO.equals(dec)) {
                period = fmod.size();
                while(true){
                    for (int i = 0; i < period; i++) {
                        val = val1.add(val2);
                        val2 = val1;
                        val1 = val;
                        fmod.add(val.remainder(mod));
                        fibNum++;
                    }
                    if(compare(period, fmod)) break;
                    period *= 2;
                }
                break;
            }
            val2 = val1;
            val1 = val;
            fmod.add(dec);
            fibNum++;
        }
        System.out.println("Period alg: " + period);
        return fmod.get(n.remainder(BigInteger.valueOf(period)).intValue()).intValue();
    }

    private static BigInteger rules(BigInteger mod) {
        double n1 = Math.log10(mod.longValue());
        int n = (int) n1;
        if(n == n1 && n > 2)
            return BigInteger.valueOf((long) (15. * (Math.pow(10, n-1))));
        n1 = Math.log10(mod.longValue()) / Math.log10(5);
        n = (int) n1;
        if(n == n1 && n > 1)
            return BigInteger.valueOf(4 * (5^n));
        n1 = Math.log10(mod.longValue() / 2) / Math.log10(5);
        n = (int) n1;
        if(n == n1 && n > 1)
            return BigInteger.valueOf(6 * n);
        return BigInteger.valueOf(-1);
    }

    private static boolean compare(int period, List<BigInteger> fib) {
        for (int i = 0; i < period; i++) {
            if(!fib.get(i).equals(fib.get(i + period))){
                return false;
            }
        }
        return true;
    }

    public static BigInteger FibList(int n) {
        if (n <= 1) return BigInteger.valueOf(n);
        BigInteger[] F = new BigInteger[n + 1];
        F[0] = BigInteger.valueOf(0);
        F[1] = BigInteger.valueOf(1);
        for (int i = 2; i < n + 1; i++) {
            F[i] = F[i - 1].add(F[i - 2]);
        }
        return F[n];

    }
}
