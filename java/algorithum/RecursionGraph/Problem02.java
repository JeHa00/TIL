package algorithum.RecursionGraph;

import java.util.Scanner;

public class Problem02 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        print(N);
    }

    public static void print(int n) {
        if (n == 0) {
            return;
        }

        int remainder = n % 2;
        print(n / 2);
        System.out.print(remainder);
    }
}
