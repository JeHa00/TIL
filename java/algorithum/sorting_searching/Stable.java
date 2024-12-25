package algorithum.sorting_searching;

import java.util.Arrays;
import java.util.Scanner;

public class Stable {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int coordinationOfStalls[] = new int[n];

        for (int i = 0; i < n; i++) {
            coordinationOfStalls[i] = input.nextInt();
        }

        Arrays.sort(coordinationOfStalls);
        solution(m, coordinationOfStalls);
    }

    public static void solution(int m, int[] coordination) {

        int lp = coordination[0];
        int rp = coordination[coordination.length - 1];
        int answer = 0;

        while (lp <= rp) {
            int distance = (lp + rp) / 2, count = 1;
            int prev = coordination[0];

            for (int i = 1; i < coordination.length; i++) {
                if ((coordination[i] - prev) >= distance) {
                    count++;
                    prev = coordination[i];
                }
            }

            if (count < m) {
                rp = distance - 1;
            } else {
                lp = distance + 1;
                answer = distance;
            }
        }
        System.out.println(answer);
    }
}
