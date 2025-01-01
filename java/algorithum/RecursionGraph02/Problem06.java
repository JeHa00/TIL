package algorithum.RecursionGraph02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Problem06 {
    static int N, M;
    static int[] values;
    static boolean[] isVisited;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        M = input.nextInt();
        values = new int[N];
        isVisited = new boolean[N];
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            values[i] = input.nextInt();
        }

        findCombinations(answer);
    }

    public static void findCombinations(ArrayList<Integer> answer) {
        if (answer.size() == M) {
            Iterator iterator = answer.iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                answer.add(values[i]);
                findCombinations(answer);
                answer.remove(answer.size() - 1);
                isVisited[i] = false;
            }
        }
    }
}
