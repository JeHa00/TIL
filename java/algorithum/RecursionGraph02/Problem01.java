package algorithum.RecursionGraph02;

import java.util.*;

public class Problem01 {

    static int[] set;
    static boolean[] isIncluded;
    static String answer = "NO";
    static int total;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        set = new int[N];
        isIncluded = new boolean[N];
        for (int i = 0; i < N; i++) {
            set[i] = input.nextInt();
            total += set[i];
        }
        dfs(0, 0);
        System.out.println(answer);
    }

    public static void dfs(int nth, int sum) {
        if (answer.equalsIgnoreCase("YES")) {
            return;
        }

        if (sum > total - sum) {
            return;
        }

        if (nth == set.length) {
            if ((total - sum) == sum) {
                answer = "YES";
            }
            return;
        }

        dfs(nth + 1, sum + set[nth]);
        dfs(nth + 1, sum);
    }
}
