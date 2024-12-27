package algorithum.RecursionGraph;

import java.util.Scanner;

// 백 트랙킹
public class Problem10MatrixBackTracking {

    public static int[][] graph;
    public static int answer;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        graph = new int[N + 1][N + 1];
        boolean[] isVisited = new boolean[N + 1];

        int M = input.nextInt();
        for (int i = 0; i < M; i++) {
            int from = input.nextInt();
            int to = input.nextInt();
            graph[from][to] = 1;
        }

        dfs(1, 5, isVisited);
        System.out.println(answer);
    }

    public static void dfs(int startNode, int endNode, boolean[] isVisited) {
        if (startNode == endNode) {
            answer++;
            return;
        }

        for (int to = 2; to < endNode + 1; to++) {
            if (graph[startNode][to] == 1 && !isVisited[to]) {
                isVisited[to] = true;
                dfs(to, endNode, isVisited);
                isVisited[to] = false;
            }
        }
    }
}
