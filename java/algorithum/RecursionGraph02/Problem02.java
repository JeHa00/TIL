package algorithum.RecursionGraph02;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem02 {
    static int max;
    static int answer;
    static ArrayList<Integer> weightList;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        max = input.nextInt();
        int N = input.nextInt();

        weightList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            weightList.add(input.nextInt());
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    public static void dfs(int currentWeight, int nth) {
        if (currentWeight > max) {
            return;
        }

        if (currentWeight == max) {
            answer = currentWeight;
            return;
        }

        if (nth == weightList.size()) {
            answer = (answer == 0) ? currentWeight : Math.max(answer, currentWeight);
            return;
        }


        dfs(currentWeight + weightList.get(nth), nth + 1);
        dfs(currentWeight, nth + 1);
    }
}
