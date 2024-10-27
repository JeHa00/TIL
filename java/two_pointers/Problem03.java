package two_pointers;

import java.util.Scanner;

public class Problem03 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        int sales[] = new int[n];
        for (int i = 0; i < n; i++) {
            sales[i] = input.nextInt();
        }

        System.out.println(solution01(sales, k));
    }

    public static int solution01(int[] sales, int k) {
        int max = 0;
        for (int i = 2; i < sales.length; i++) {
            int sum = 0;
            for (int j = i - 2; j <= i; j++) {
                sum += sales[j];
            }
            max = Math.max(max, sum);
        }

        return max;
    }
}
