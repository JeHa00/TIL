package array;

import java.util.Scanner;

/*
* 소수 구하기(에라토스테네스 체)
* 소수 구하기는 제곱근보다 에라토스테네스 체가 제일 빠르다.
*
* 자연수 N이 입력되면 1부터 N까지의 소수의 개수를 출력하는 프로그램을 작성하세요.
* 만약 20이 입력되면 1부터 20까지의 소수는 2, 3, 5, 7, 11, 13, 17, 19로 총 8개입니다.
* 제한시간은 1초입니다.
▣ 입력설명
첫 줄에 자연수의 개수 N(2<=N<=200,000)이 주어집니다.
▣ 출력설명
첫 줄에 소수의 개수를 출력합니다.
▣ 입력예제 1 20
▣ 출력예제 1 8
* */

public class Problem05 {

    public static int solution01(int amount) {
        int count = 0;
        boolean[] filter = new boolean[amount + 1];

        for (int i = 2; i <= amount; i++) {
            if (filter[i] == false) {
                count++;
                for (int j = i; j <= amount; j += i) {
                    filter[j] = true;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        System.out.println(solution01(N));
    }
}
