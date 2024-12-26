package algorithum.RecursionGraph;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem07 {
    static Deque<Node> willVisit = new ArrayDeque<>();

    public static void main(String[] args) {
        Node first = new Node(1);
        init(first);
        bfs(first);
    }

    public static void bfs(Node first) {
        willVisit.add(first);

        int level = 0;

        while (!willVisit.isEmpty()) {
            int nodeAmountOnTheLevel = willVisit.size();
            System.out.println("level: " + level); // 레벨 표시

            for (int i = 0; i < nodeAmountOnTheLevel; i++) {
                Node node = willVisit.pollLast();
                System.out.print(node.value + " ");

                if (node.prev != null) {
                    willVisit.offerFirst(node.prev);
                }

                if (node.next != null) {
                    willVisit.offerFirst(node.next);
                }
            }
            System.out.println();
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

        Node() {

        }

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
