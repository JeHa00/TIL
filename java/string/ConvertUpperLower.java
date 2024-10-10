package string;

import java.util.Scanner;

public class ConvertUpperLower {
    public static String solution01(String string) {
        String answer = "";
        for (char c : string.toCharArray()) {
            if (Character.isUpperCase(c)) {
                answer += Character.toLowerCase(c);
            } else {
                answer += Character.toUpperCase(c);
            }
        }

        return answer;
    }

    public static String solution02(String string) {
        String answer = "";

        for (char c : string.toCharArray()) {
            if (c < 97) {
                answer += (char) (c + 32);
            } else {
                answer += (char) (c - 32);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.next();
        System.out.println(ConvertUpperLower.solution01(str));
        System.out.println(ConvertUpperLower.solution02(str));
    }
}
