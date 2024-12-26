package algorithum.RecursionGraph;


import java.util.ArrayDeque;
import java.util.Deque;

// 전위, 중위 그리고 후위의 차이는 이 정의대로 자기 자신 호출을 코드 어느 순서에서 하냐에 따라 결정된다.
// 순회는 스택을 사용하는 것보다 재귀 함수를 사용하는 것이 구조가 단순하다.
public class Problem05 {

    public static void main(String[] args) {
        Node root = new Node(1);
        init(root);

        System.out.println("----- 전위 순회 -----");
        preOrder(root);
        System.out.println();

        System.out.println("\n----- 중위 순회 -----");
        inOrder(root);
        System.out.println();

        System.out.println("\n----- 후위 순회 -----");
        postOrder(root);
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

    static void preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.value + " "); // 맨 앞
        preOrder(node.prev);
        preOrder(node.next);

    }


    static void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.prev);
        System.out.print(node.value + " "); // 중간
        inOrder(node.next);
    }



    static void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.prev);
        postOrder(node.next);
        System.out.print(node.value + " "); // 맨 뒤
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
