package algorithum.string;

import java.util.Scanner;

public class Palindrome02 {
    public static String solution01(String string) {
        char[] chars = string.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (char s : chars) {
            if (Character.isAlphabetic(s)) {
                sb.append(s);
            }
        }

        String sentence = sb.toString();
        String reverse = sb.reverse().toString();

        return (sentence.equalsIgnoreCase(reverse)) ? "YES" : "NO";
    }

    public static String solution02(String string) {
        string = string.toUpperCase().replaceAll("[^A-Z]", "");
        String reverse = new StringBuilder(string).reverse().toString();

        return (string.equals(reverse)) ? "YES" : "NO";
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.nextLine();
        System.out.println(solution01(string));
        System.out.println(solution02(string));
    }
}
