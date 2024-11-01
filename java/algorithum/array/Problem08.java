package algorithum.array;

import java.util.Scanner;

public class Problem08 {
    public static void solution01(int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            int nth = 1;
            for (int j = 0; j < scores.length / 2; j++) {
                int score = scores[i];
                if (scores[j] > score) {
                    nth++;
                }

                if (scores[scores.length - 1 - j] > score) {
                    nth++;
                }
            }
            System.out.print(nth + " ");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int total = input.nextInt();
        int scores[] = new int[total];
        for (int i = 0;  i < scores.length; i++) {
            scores[i] = input.nextInt();
        }
        solution01(scores);
    }
}
