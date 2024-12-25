package algorithum.string;

import java.util.Scanner;

public class ReverseSpecificChar {
    public static String solution01(String string) {
        char[] chars = string.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            if (!Character.isAlphabetic(chars[left])) {
                left++;
            } else if (!Character.isAlphabetic(chars[right])) {
                right--;
            } else {
                char c = chars[left];
                chars[left] = chars[right];
                chars[right] = c;
                left++;
                right--;
            }

        }

        return String.valueOf(chars);
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        System.out.println(solution01(string));
    }
}
