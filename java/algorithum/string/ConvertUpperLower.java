package algorithum.string;

import java.util.Scanner;

public class ConvertUpperLower {
    public static String solution01(String string) {

        StringBuilder answer = new StringBuilder();
        for (char w : string.toCharArray()) {
            char convertedC = (Character.isUpperCase(w)) ? Character.toLowerCase(w) : Character.toUpperCase(w);
            answer.append(convertedC);
        }

        return answer.toString();
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
