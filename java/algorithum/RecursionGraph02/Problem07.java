package algorithum.RecursionGraph02;

import java.util.Scanner;

// 조합의 경우의 수 구하기
public class Problem07 {
    static int[][] nCr = new int[35][35];

    public static void main(String[] args)  {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int r = input.nextInt();
        System.out.println(dfs(n, r));
    }

    public static int dfs(int n, int r) {
        if (n == r || r == 0) {
            return 1;
        }

        if (nCr[n][r] == 0) {
            nCr[n][r] = dfs(n - 1, r - 1) + dfs(n - 1, r);
        }
        return nCr[n][r];
    }
}
