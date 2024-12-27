package algorithum.RecursionGraph;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem09 {

    static Deque<Node> queue = new ArrayDeque<>();
    static int level;

    public static void main(String[] args) {
        Node root = new Node(1);
        init(root);
        bfs(root);
        System.out.println(dfs(0, root));
    }

    // 최단 거리이므로 bfs 다.
    static void bfs(Node root) {
        queue.offerFirst(root);

        int answer = 0;

        while (!queue.isEmpty()) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                Node x = queue.pollLast();
                if (x.left != null) {
                    queue.offerFirst(x.left);
                }

                if (x.right != null) {
                    queue.offerFirst(x.right);
                }

                if (x.left == null || x.right == null) {
                    answer = (answer == 0) ? level : Math.min(level, answer);
                }
            }
            level++;
        }

        System.out.println(answer);
    }

    static int dfs(int level, Node root) {
        if (root.left == null && root.right == null) {
            return level;
        }

        return Math.min(dfs(level + 1, root.left), dfs(level + 1, root.right));
    }

    static void init(Node first) {
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);

        first.setRight(two);
        first.setLeft(third);

        two.setRight(four);
        two.setLeft(five);
    }


static class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
    }

    void setLeft(Node node) {
        left = node;
    }

    void setRight(Node node) {
        right = node;
    }
    }


}
