package string;

import java.util.Scanner;

public class RemoveDuplication {
    public static String solution01(String string) {
        char[] chars = string.toCharArray();
        char answers[] = new char[chars.length];
        int size = 0;

        for (char c : chars) {
            boolean exist = false;
            for (char a: answers) {
                if (c == a) {
                    exist = true;
                }
            }

            if (!exist) {
                answers[size] = c;
                size++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char answer : answers) {
            if (Character.isAlphabetic(answer)) {
                sb.append(answer);
            }
        }

        return sb.toString();
    }

    public static String solution02(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.indexOf(string.charAt(i)) == i) {
                sb.append(string.charAt(i));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        System.out.println(solution01(string));
        System.out.println(solution02(string));
    }
}
