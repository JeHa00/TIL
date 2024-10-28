package two_pointers;

import java.util.Scanner;

public class Problem05 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println(solution01(n));
    }

    public static int solution01(int n) {
        int count = 0;
        for (int i = 2; i < n / 2; i++) {
            for (int j = 1; j <= n; j++) {
                int sum = 0;
                for (int k = j; k < j + i; k++) {
                    sum += k;
                }
                if (sum == n) {
                    count++;
                }
            }

        }

        return count;
    }
}
