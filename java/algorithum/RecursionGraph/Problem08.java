package algorithum.RecursionGraph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Problem08 {
    static boolean[] isVisited = new boolean[10001];
    static int[] dx = {-1, 1, 5};
    static Deque<Integer> queue = new ArrayDeque<>();
    static int level;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int S = input.nextInt();
        int E = input.nextInt();
        System.out.println(bfs(S, E));
    }

    public static int bfs(int S, int E) {
        isVisited[S] = true;
        queue.offerFirst(S);

        while (!queue.isEmpty()) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                int x = queue.pollLast();

                for (int j = 0; j < dx.length; j++) {
                    int nx = x + dx[j];
                    if (nx == E) return level + 1;

                    if (1 <= nx && nx < 10000  && !isVisited[nx]) {
                        isVisited[nx] = true;
                        queue.offerFirst(nx);
                    }
                }
            }
            level++;
        }
        return 0;
    }
}
