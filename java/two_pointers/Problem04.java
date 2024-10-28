package two_pointers;

import java.util.Scanner;

public class Problem04 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int sales[] = new int[n];
        for (int i = 0; i < n; i++) {
            sales[i] = input.nextInt();
        }

        System.out.println(solution01(sales, m));
    }

    public static int solution01(int[] values, int m) {
        int count = 0;

        for (int i = 0; i < values.length; i++) {
            int sum = 0;
            for (int j = i; j < values.length; j++) {
                sum += values[j];
                if (sum > m) {
                    break;
                }

                if (sum == m) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }
}
