package algorithum.sorting_searching;

import java.util.ArrayDeque;
import java.util.Scanner;

public class LeastRecentlyUsed {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int s = input.nextInt();
        int n = input.nextInt();
        int values[] = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = input.nextInt();
        }

        solution(s, values);
    }

    public static void solution(int s, int[] values) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int value : values) {
            if (deque.contains(value)) {
                deque.remove(value);
            }

            deque.offerFirst(value);

            while (deque.size() > s) {
                deque.pollLast();
            }
        }

        for (Integer value :  deque) {
            System.out.print(value.intValue() + " ");
        }
    }
}
