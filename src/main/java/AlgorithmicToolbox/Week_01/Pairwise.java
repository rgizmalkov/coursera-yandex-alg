package AlgorithmicToolbox.Week_01;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by elion on 07.02.2017.
 */
public class Pairwise {
    public static void main(String[] args) {
        Pairwise.pairwiseBySequence();
    }

    private static void pairwiseWithSort(){
        Scanner sc = new Scanner(System.in);
        final Integer SIZE = sc.nextInt();
        Long[] a = new Long[SIZE];
        for(int i = 0; i < SIZE; i++){
            a[i] = sc.nextLong();
        }
        Arrays.sort(a);
        System.out.println(a[SIZE-1]*a[SIZE-2]);
    }

    private static void pairwiseBySequence(){
        Scanner sc = new Scanner(System.in);
        final Integer SIZE = sc.nextInt();

        long maxValue = -1;
        long preMaxValue = -1;

        for (int i = 0; i < SIZE; i++) {
            long readableValue = sc.nextLong();
            if(readableValue >= preMaxValue){
                if(readableValue >= maxValue){
                    preMaxValue = maxValue;
                    maxValue = readableValue;
                }else {
                    preMaxValue = readableValue;
                }
            }
        }
        System.out.println(maxValue*preMaxValue);
    }
}
