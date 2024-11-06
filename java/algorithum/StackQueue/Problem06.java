package algorithum.StackQueue;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Problem06 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        System.out.println(solution01(n, k));
    }

    public static int solution01(int n, int k) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            queue.offerLast(i);
        }
        int count = 0;
        while (queue.size() > 1) {
            Integer prince = queue.pollFirst();
            count++;
            if (count != k) {
                queue.offerLast(prince);
            } else {
                count = 0;
            }
        }

        return queue.pollFirst();
    }
}
