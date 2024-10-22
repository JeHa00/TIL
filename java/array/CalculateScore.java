package array;

import java.util.Scanner;

public class CalculateScore {
    public static int solution01(int[] answers) {
        int count = 0;
        int score = 0;

        for (int i = 0;  i < answers.length; i++) {
            count = (answers[i] == 0) ? 0 : ++count;
            score += count;
        }
        // 10
        // 1 0 1 0 1 1 1 0 0 1
        return score;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int total = input.nextInt();
        int answers[] = new int[total];
        for (int i = 0;  i < answers.length; i++) {
            answers[i] = input.nextInt();
        }
        System.out.println(solution01(answers));
    }
}
