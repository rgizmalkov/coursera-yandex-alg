package AlgorithmicToolbox.Week_01;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by elion on 07.02.2017.
 */
public class Pairwise {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final Integer SIZE = sc.nextInt();
        Long[] a = new Long[SIZE];
        for(int i = 0; i < SIZE; i++){
            a[i] = sc.nextLong();
        }
        Arrays.sort(a);
        System.out.println(a[SIZE-1]*a[SIZE-2]);
    }
}
