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
            if (tempChange + coins[i] <= change) {
                dfs(tempChange + coins[i], amount + 1);
            }
//            dfs(tempChange, amount);
//            동전을 사용하지 않는 경우에 대해 추가한 것이지만, 이 코드로 무한 호출이 발생하므로 적합하지 않은 코드다.
//            그리고 동전은 중복으로 사용되므로 해당 동전에 대해 사용하지 않는 경우는 적합하지 않다.

        }
    }
}
