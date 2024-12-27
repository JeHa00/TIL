package algorithum.RecursionGraph;

import java.util.ArrayList;
import java.util.Scanner;

// 인접행렬은 크기가 커질수록 메모리 낭비가 커진다. 그래서 정점의 개수가 많아지면 참조 방식을 사용하자.
public class Problem11LinkedListBackTracking {

    public static int N, M, answer;
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static boolean[] isVisited;

    public static void main(String[] args) {
        init();
        dfs(1, 5);
        System.out.println(answer);
    }

    public static void init() {
        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        M = input.nextInt();
        isVisited = new boolean[N + 1];
        isVisited[1] = true;

        for (int i = 0; i <= N; i++) { // N + 1개를 생성하기 위해 <= N 으로 입력
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int from = input.nextInt();
            int to = input.nextInt();
            graph.get(from).add(to);
        }
    }

    public static void dfs(int start, int end) {
        if (start == end) {
            answer++;
            return;
        }

        for (int next : graph.get(start)) {
            if (!isVisited[next]) {
                isVisited[next] = true;
                dfs(next, end);
                isVisited[next] = false;
            }
        }
    }
}

