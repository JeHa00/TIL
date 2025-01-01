package algorithum.RecursionGraph02;

import java.util.Scanner;

public class Problem05 {
    static int N, answer, change;
    static int[] coins;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = input.nextInt();
        }
        change = input.nextInt();

        dfs(0, 0);
        System.out.println(answer);
    }

    public static void dfs(int tempChange, int amount) {
        if (tempChange > change) {
            return;
        }

        if (tempChange == change) {
            answer = (answer == 0) ? amount : Math.min(answer, amount);
            return;
        }

        for (int i = 0; i < coins.length; i++) {
            dfs(tempChange + coins[i], amount + 1);
//            dfs(tempChange, amount); 예를 들어 5원이 3개인 것은 그대로인 값에서 시작하는 것이 아닌 5 5 5에서 시작

        }
    }
}
