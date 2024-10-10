package string;

import java.util.Scanner;

public class WordInSentence {
    public static String solution01(String string) {
        String answer = "";
        int m = Integer.MIN_VALUE;
        String[] s = string.split(" ");
        for (String x : s) {
            if (x.length() > m) {
                m = x.length();
                answer = x;
            }
        }
        return answer;
    }

    public static String solution02(String string) {
        String answer = "";
        int m = Integer.MIN_VALUE, pos;
        while ((pos = string.indexOf(" ")) != -1) {
            String tmp = string.substring(0, pos);
            int len = tmp.length();
            if (len > m) {
                m = len;
                answer = tmp;
            }
            string = string.substring(pos + 1);
        }
        if (string.length() > m) {
            answer = string;
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.nextLine(); // 문장이므로
        System.out.println(WordInSentence.solution01(string));
    }
}
