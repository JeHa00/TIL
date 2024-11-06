package algorithum.StackQueue;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 5
 0 0 0 0 0
 0 0 1 0 3
 0 2 5 0 1
 4 2 4 4 2
 3 5 1 3 1
 8
 1 5 3 5 1 2 1 4
 * */

public class Problem03 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        ArrayDeque<Integer>[] board = new ArrayDeque[n];
        for (int i = 0; i < n; i++) {
            board[i] = new ArrayDeque<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Integer value = input.nextInt();
                if (value != 0) {
                    board[j].offerLast(value);
                }
            }
        }

        int m = input.nextInt();
        int moves[] = new int[m];
        for (int i = 0; i < m; i++) {
            moves[i] = input.nextInt();
        }

        System.out.println(solution01(board, moves));
    }

    public static int solution01(ArrayDeque[] board, int[] moves) {
        int count = 0;

        ArrayDeque<Integer> pocket = new ArrayDeque<>();
        for (int m : moves) {
            if (board[m - 1].size() != 0) {
                Integer value = (Integer) board[m - 1].pollFirst();
                if (pocket.peekFirst() == value) {
                    pocket.pollFirst();
                    count += 2;
                } else {
                    pocket.push(value);
                }
            }
        }
        return count;
    }
}