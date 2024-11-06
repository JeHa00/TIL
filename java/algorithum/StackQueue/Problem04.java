package algorithum.StackQueue;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Problem04 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        System.out.println(solution01(string));
    }

    public static String solution01(String string) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (Character.isDigit(c)) {
                stack.offerLast(String.valueOf(c));
            } else {
                int value01 = Integer.valueOf(stack.pollLast());
                int value02 = Integer.valueOf(stack.pollLast());
                if (c == '+') {
                    stack.offerLast(String.valueOf(value01 + value02));
                } else if (c == '-') {
                    int sum = (value02 >= value01) ? value02 - value01 : value01 - value02;
                    stack.offerLast(String.valueOf(sum));
                } else if (c == '*') {
                    stack.offerLast(String.valueOf(value01 * value02));
                }
            }
        }

        return stack.pollLast();
    }
}
