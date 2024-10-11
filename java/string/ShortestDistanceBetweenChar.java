package string;

import java.util.ArrayList;
import java.util.Scanner;

public class ShortestDistanceBetweenChar {
    public static void solution01(String string, char c) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == c) {
                list.add(Integer.valueOf(i));
            }
        }

        int answer[] = new int[string.length()];
        for (int i = 0; i < string.length(); i++) {
            int min = string.length();
            for (Integer integer : list) {
                int value = Math.abs(integer.intValue() - i);
                min = Math.min(min, value);
            }
            answer[i] = min;
        }
        for (int i : answer) {
            System.out.print(i + " ");
        }
    }

    public static void solution02(String string, char c) {
        int[] answer = new int[string.length()];
        int indexOfC = string.length();
        for (int i = 0;  i < string.length(); i++) {
            if (string.charAt(i) == c) {
                indexOfC = 0;
            } else {
                indexOfC++;
            }
            answer[i] = indexOfC;
        }

        indexOfC = string.length();
        for (int i = string.length() - 1; i >= 0; i--) {
            if (string.charAt(i) == c) {
                indexOfC = 0;
            } else {
                indexOfC++;
                answer[i] = Math.min(answer[i], indexOfC);
            }
        }

        for (int i : answer) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] string = input.nextLine().split(" ");
        solution01(string[0], string[1].charAt(0));
    }
}
