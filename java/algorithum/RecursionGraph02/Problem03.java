package algorithum.RecursionGraph02;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem03 {
    static int N, M, answer;
    static ArrayList<Problem> problems = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        M = input.nextInt();

        for (int i = 0; i < N; i++) {
            int score = input.nextInt();
            int time = input.nextInt();
            problems.add(new Problem(score, time));
        }

        dfs(0, 0, 0);
        System.out.println(answer);
    }

    public static void dfs(int nth, int totalScore, int totalTime) {
        if (totalTime > M) {
            return;
        }

        if (totalTime == M) {
            answer = totalScore;
            return;
        }

        if (nth == problems.size()) {
            answer = (answer == 0) ? totalScore : Math.max(answer, totalScore);
            return;
        }

        Problem problem = problems.get(nth);
        dfs(nth + 1, totalScore, totalTime);
        dfs(nth + 1, totalScore + problem.score, totalTime + problem.time);
    }

    static class Problem {
        int score;
        int time;

        Problem(int score, int time) {
            this.score = score;
            this.time = time;
        }
    }
}
