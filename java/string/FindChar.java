package string;

import java.util.Scanner;

public class FindChar {
    public static int solution(String string, char c) {
        int count = 0;

        for (char s : string.toCharArray()) {
            if (s == c) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.next();
        char c = input.next().charAt(0);
        System.out.println(FindChar.solution(str, c));
    }
}
