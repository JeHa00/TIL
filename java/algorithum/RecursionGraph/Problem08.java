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

    // 방문 여부를 점검하는 이유
    // 문제를 다시 풀면서 이 이유에 대해 이해를 하지 못한 것을 발견했다.
    // '더 나은 경로로 특정 지점에 도착했을 수도 있지 않느냐?' 라는 의문을 가졌다.
    // 하지만 나중에 가서 동일한 지점에 방문했다면 level이 증가했기 때문에 반드시 더 긴 경로로 도달했다.
    // 그리고 방문했던 지점에 대해 다시 -1, 1, 5를 연산하기 때문에 반복하게 된다.
    // 그래서 반드시 방문 여부를 점검해야 한다.
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
