package algorithum.RecursionGraph02;

import java.util.Scanner;

public class Problem04 {
    static int N, M;


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        M = input.nextInt();
        solution01();
        solution02(1, 1);
    }

    public static void solution01() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.println(i + " " + j);
            }
        }
    }

    public static void solution02(int i, int j) {
        if (i == N + 1) {
            return;
        }

        if (j == N + 1) {
            i++;
            j = 1;
        }

        System.out.println(i + " : " + j);
        solution02(i, j + 1);
    }
}
