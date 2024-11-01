package algorithum.array;

import java.util.Scanner;

/**
 * 뒤집은 소수
 * N개의 자연수가 입력되면 각 자연수를 뒤집은 후 그 뒤집은 수가 소수이면 그 소수를 출력하 는 프로그램을 작성하세요.
 * 예를 들어 32를 뒤집으면 23이고, 23은 소수이다. 그러면 23을 출 력한다. 단 910를 뒤집으면 19로 숫자화 해야 한다.
 * 첫 자리부터의 연속된 0은 무시한다.

 * ▣ 입력설명
 * 첫 줄에 자연수의 개수 N(3<=N<=100)이 주어지고, 그 다음 줄에 N개의 자연수가 주어진다. 각 자연수의 크기는 100,000를 넘지 않는다.

 * ▣ 출력설명
 * 첫 줄에 뒤집은 소수를 출력합니다. 출력순서는 입력된 순서대로 출력합니다.

 * ▣ 입력예제 1
 * 9
 * 32 55 62 20 250 370 200 30 100

 * ▣ 출력예제 1 23 2 73 2 3
 * */

public class Problem06 {
    public static void solution01(String[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            String reversedValue = new StringBuilder(numbers[i]).reverse().toString();
            int value = Integer.valueOf(reversedValue);

            if (isPrime(value)) {
                System.out.print(value + " ");
            }

        }
    }

    public static boolean isPrime(int value) {
        if (value == 1) {
            return false;
        }

        boolean isPrime = true;
        for (int j = 2; j < value; j++) {
            if (value % j == 0) {
                isPrime = false;
            }
        }

        return isPrime;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int total = input.nextInt();
        String numbers[] = new String[total];
        for (int i = 0;  i < numbers.length; i++) {
            numbers[i] = input.next();
        }
        solution01(numbers);
    }
}
