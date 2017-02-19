package AlgorithmicToolbox.Week_02.fibonacci;

import Utils.abstraction.AbstractAlgorithm;
import Utils.annotations.TestingAlgorithms;
import Utils.annotations.TimeLimit;
import Utils.annotations.Timing;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by elion on 19.02.2017.
 */
@SuppressWarnings("Duplicates")
public class Fibonacci extends AbstractAlgorithm {
    private int n;

    @Override
    public void read() {
        this.n = scanner.nextInt();
    }

    @Override
    public void prepare() {
    }

//    @Override
//    @TimeLimit(limit = 1)
//    @TestingAlgorithms(path = "classpath:AlgorithmicToolbox.Week_01.Fibonacci.tests")
    @Timing
    public BigInteger alg() {
        if(n <= 1) return BigInteger.valueOf(n);
        BigInteger[] F = new BigInteger[n+1];
        F[0] = BigInteger.valueOf(0);
        F[1] = BigInteger.valueOf(1);
        for (int i = 2; i < n+1; i++) {
            F[i] = F[i - 1].add(F[i - 2]);
        }
        return F[n];
    }

    public static void main(String[] args) {
        Fibonacci fb = new Fibonacci();
        fb.alg();
        System.out.println(fb.alg());
    }
}
