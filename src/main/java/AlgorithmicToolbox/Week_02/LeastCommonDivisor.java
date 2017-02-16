//package AlgorithmicToolbox.Week_02;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by elion on 15.02.2017.
 */
public class LeastCommonDivisor {
    private final int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(new LeastCommonDivisor(sc.nextInt(), sc.nextInt()).getAnswer());
    }


    public LeastCommonDivisor(int a, int b){
        this.answer = lcd(EuclideanGCD(a, b));
    }


    private int lcd(int val) {
        int answer = 1;
        boolean[] sign = new boolean[val];
        int sqrt = (int) Math.sqrt(val) + 1;
        for(int i = 2; i < sqrt; i++){
            answer = val % i;
            if(answer == 0) return answer;
            if(!sign[i]){
                int ii = i*i;
                for (int j = ii; j < val; j+=i) {
                    sign[j] = true;
                }
            }
        }
        return val;

    }

    private int EuclideanGCD(int val1, int val2) {
        if(val2 == 0){
            return val1;
        }else {
            return EuclideanGCD(val2, val1%val2);
        }
    }

    public int getAnswer() {
        return answer;
    }
}
