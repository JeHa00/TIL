package algorithum.string;

import java.util.Scanner;

public class ExtractOnlyNumbers {
    public static int solution01(String string) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                sb.append(string.charAt(i));
            }
        }

        return Integer.parseInt(sb.toString());

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        System.out.println(solution01(string));
    }
}
