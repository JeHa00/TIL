package algorithum.StackQueue;

import java.util.Scanner;

public class Problem05 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        System.out.println(solution01(string));
    }

    public static int solution01(String string) {
        int plate = 0;
        int answer = 0;

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            char d = ' ';
            if (i != 0) {
                d = string.charAt(i - 1);
            }

            if (c == '(') {
                plate++;
            } else if (c == ')' && d == '(') {
                answer += --plate;
            } else {
                plate--;
                answer++;
            }
        }

        return answer;
    }
}
