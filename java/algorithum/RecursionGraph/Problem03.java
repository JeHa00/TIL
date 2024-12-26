package algorithum.RecursionGraph;

import java.util.Scanner;

public class Problem03 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        System.out.println(factorial(N));
    }

    public static int factorial(int N) {
        if (N == 1) {
            return N;
        }

        return N * factorial(N - 1);
    }
}
