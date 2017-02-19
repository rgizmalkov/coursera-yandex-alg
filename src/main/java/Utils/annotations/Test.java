package Utils.annotations;

import AlgorithmicToolbox.Week_02.Fibonacci;

/**
 * Created by romanizmalkov on 18.02.17.
 */
public class Test {
    public static void main(String[] args) {
        Test.a();
        new Test().b();
        Fibonacci.FibList(100);
    }

    @Timing
    private static void a(){
        System.out.println("A");
    };

    @Timing
    public void b(){
        System.out.println("B");
    }
}
