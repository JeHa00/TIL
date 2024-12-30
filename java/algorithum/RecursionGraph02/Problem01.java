package algorithum.RecursionGraph02;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Problem01 {
    static int[] dx = {-1, 1, 5};


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int S = input.nextInt();
        int E = input.nextInt();

        bfs(S, E);
    }

    public static void bfs(int S, int E) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offerFirst(S);
        boolean[] isVisited = new boolean[10_001];
        isVisited[S] = true;
        int level = 0;

        while (!queue.isEmpty()) {
            int length = queue.size();

            for (int i = 0; i < length; i++) {
                int currentPoint = queue.pollLast();
                for (int j = 0; j < dx.length; j++) {
                    int x = currentPoint + dx[j];
                    if (x == E) {
                        System.out.println(level + 1);
                        return;
                    }

                    if (1 <= x && x <= 10_000) {
                        isVisited[x] = true;
                        queue.offerFirst(x);
                    }
                }
            }
            level++;
        }
    }

    static void init(Node first) {
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);

        first.setPrev(two);
        first.setNext(third);

        two.setPrev(four);
        two.setNext(five);

        third.setPrev(six);
        third.setNext(seven);
    }

    static class Node {
        int value;
        Node prev;
        Node next;

        Node(int value) {
            this.value = value;
        }

        void setPrev(Node prev) {
            this.prev = prev;
        }

        void setNext(Node next) {
            this.next = next;
        }
    }


}
