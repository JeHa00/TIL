package algorithum.two_pointers;

import java.util.Scanner;

public class Problem06 {
    public static int solution01(int[] values, int m) {
        int max = 0, lp = 0, count = 0;

        for (int rp = 0; rp < values.length; rp++) {
            if (values[rp] == 0) {
                count++;
            }

            while (count > m) {
                if (values[lp] == 0) {
                    count--;
                }
                lp++;
            }
            max = Math.max(max, rp - lp + 1);
        }
        return max;
    }

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
}
