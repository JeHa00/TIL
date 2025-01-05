package algorithum.RecursionGraph;

import java.util.*;

public class Problem12 {
    static int N, M, answer, level;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] isVisited;

    public static void main(String[] args) {
        init();

        for (int i = 2; i <= N; i++) {
            bfs(1, i);
            System.out.println(i + " : " + answer);
        }
    }

    public static void init() {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        M = input.nextInt();

        isVisited = new boolean[N + 1];

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList());
        }

        for (int i = 0; i < M; i++) {
            int from = input.nextInt();
            int to = input.nextInt();
            graph.get(from).add(to);
        }
    }

    public static void dfs(int start, int end, int edgeCount) {
        if (start == end) {
            answer = (answer == 0) ? edgeCount : Math.min(answer, edgeCount);
            return;
        }

        for (int to : graph.get(start)) {
            if (!isVisited[to]) {
                isVisited[to] = true;
                edgeCount++;
                System.out.println("start: " + start + " / to: " + to + " / edgeCount: " + edgeCount);
                dfs(to, end, edgeCount);
                isVisited[to] = false;
            }
        }
    }

    public static void bfs(int start, int end) {
        answer = 0;
        level = 0;
        isVisited[start] = true;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offerFirst(start);

        while (!queue.isEmpty()) {
            int nodeAmountOnTheLevel = queue.size();
            for (int i = 0; i < nodeAmountOnTheLevel; i++) {
                start = queue.pollLast();

                if (start == end) {
                    answer = (answer == 0) ? level : Math.min(answer, level);
                    queue.clear();
                    break;
                }

                for (int to : graph.get(start)) {
                    if (!isVisited[to]) {
                        isVisited[to] = true;
                        queue.offerFirst(to);
                    }
                }
            }
            level++;
        }
    }
}
