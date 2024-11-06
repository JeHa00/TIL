package algorithum.StackQueue;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Problem02 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        solution01(string);
    }

    public static void solution01(String string) {
        ArrayDeque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '(') {
                deque.push(c);
            } else if (c == ')' && deque.peek() == '(') { // deque.peekLast()
                deque.pop(); // deque.pollLast()
            } else if ((c != '(' && c != ')') && deque.isEmpty()) {
                System.out.print(c);
            }
        }

    }
}
