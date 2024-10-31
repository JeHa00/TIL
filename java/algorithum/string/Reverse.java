package string;

import java.util.Scanner;

public class Reverse {
    public static void solution01(String[] words) {
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder();
            char[] chars = words[i].toCharArray();
            for (int j = chars.length - 1; j >= 0; j--) {
                sb.append(chars[j]);
            }
            System.out.println(sb);
        }
    }

    public static void solution02(String[] words) {

        for (String word : words) {
            char[] chars = word.toCharArray();
            int left = 0;
            int right = chars.length - 1;

            while (left < right) {
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                left++;
                right--;
            }
            System.out.println(String.valueOf(chars));
        }
    }

    public static void solution03(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String word = new StringBuilder(words[i]).reverse().toString();
            System.out.println(word);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int amount = input.nextInt();
        String words[] = new String[amount];
        for (int i = 0; i < amount; i++) {
            words[i] = input.next();
        }
        solution01(words);
        solution02(words);
        solution03(words);
    }
}
