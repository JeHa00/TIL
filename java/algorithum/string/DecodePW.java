package string;

import java.util.Scanner;

public class DecodePW {
    public static String solution01(String string) {
        StringBuilder answer = new StringBuilder();
        int LENGTH_OF_A_WORD = 7;
        int start = 0;

        while (start + LENGTH_OF_A_WORD <= string.length()) {
            String substring = string.substring(start, start + 7);
            start += LENGTH_OF_A_WORD;

            // 이진수로 변환
            StringBuilder binaryNumber = new StringBuilder();
            for (char c : substring.toCharArray()) {
                if (c == '#') {
                    binaryNumber.append(1);
                } else {
                    binaryNumber.append(0);
                }
            }


            // 십진수로 변환
            String binaryString = binaryNumber.toString();
            int decimal = 0;
            for (int i = 0; i < binaryString.length(); i++) {
                // char을 바로 int 형으로 변환하면 아스키코드를 사용해 변환되므로
                // getNumericValue() 메서드를 사용한다.
                int value = Character.getNumericValue(binaryString.charAt(i));
                decimal += value * Math.pow(2, binaryString.length() - 1 - i);
            }

            // 알파벳으로 변환
            char c = (char) decimal;
            answer.append(c);
        }
        return answer.toString();
    }

    public static String solution02(String string) {
        StringBuilder answer = new StringBuilder();
        int LENGTH_OF_A_WORD = 7;
        int start = 0;

        // 이진수로 변환
        string = string.replace("#", "1").replace("*", "0");

        while (start + LENGTH_OF_A_WORD <= string.length()) {
            String substring = string.substring(start, start + 7);
            start += LENGTH_OF_A_WORD;

            int decimal = Integer.parseInt(substring, 2);
            answer.append((char) decimal);
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        System.out.println(solution01(string));
        System.out.println(solution02(string));
    }
}
