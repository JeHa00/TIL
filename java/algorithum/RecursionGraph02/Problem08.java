package algorithum.RecursionGraph02;

import java.util.Scanner;

public class Problem08 {
    static int[][] coefficients;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] N_F = input.nextLine().split(" ");
        int N = Integer.valueOf(N_F[0]);
        int F = Integer.valueOf(N_F[1]);

        coefficients = new int[N][N];







    }

    public static int calculateCoeffi(int n, int r) {
        if (coefficients[n][r] > 0) {
            return coefficients[n][r];
        }

        if (n == r || r == 0) {
            return 1;
        }

        return coefficients[n][r] = calculateCoeffi(n - 1, r - 1) + calculateCoeffi(n - 1, r);
    }
}
