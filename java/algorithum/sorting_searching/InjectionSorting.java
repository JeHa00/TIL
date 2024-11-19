package algorithum.sorting_searching;

import java.util.Arrays;
import java.util.Scanner;

public class InjectionSorting {
    public static void solution01(int[] values) {
        for (int i = 1; i < values.length; i++) {
            int target = values[i];
            for (int j = 0; j < i; j++) {
                if (values[j] > target) {
                }
            }
        }

        System.out.println(Arrays.toString(values));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int values[] = new int[n];
        for (int i = 0; i < values.length; i++) {
            values[i] = input.nextInt();
        }
        solution01(values);
    }
}
