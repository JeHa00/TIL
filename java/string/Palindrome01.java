package string;

import java.util.Scanner;

public class Palindrome01 {
    public static String solution01(String string) {
        string = string.toUpperCase();
        int left = 0;
        int right = string.length() - 1;
        String answer = "NO";

        while (left < right) {
            if (string.charAt(left) != string.charAt(right)) {
                return answer;
            }
            left++;
            right--;
        }
        answer = "YES";
        return answer;
    }

    public static String solution02(String string) {
        String answer = "YES";
        String reverse = new StringBuilder(string).reverse().toString();
        if (string.equalsIgnoreCase(reverse)) {
            return answer;
        }
        answer = "NO";
        return answer;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        System.out.println(solution01(string));
        System.out.println(solution02(string));
    }
}
