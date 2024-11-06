package algorithum.StackQueue;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Problem01 {
    public static String solution01(String string) {
        String answer = "NO";
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (c == ')' && stack.peek() == '(') { // stack.peekLast()
                stack.pop();
            }

            if (c == '(') {
                stack.push(c); // stack.offerLast()
            }
        }

        if (stack.isEmpty()) {
            answer = "YES";
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        System.out.println(solution01(string));
    }
}
