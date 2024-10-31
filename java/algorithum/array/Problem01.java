package array;

import java.util.LinkedList;
import java.util.Scanner;

/*
* N(1<=N<=100)개의 정수를 입력받아, 자신의 바로 앞 수보다 큰 수만 출력하는 프로그램을 작성하세요.
* (첫 번째 수는 무조건 출력한다)
▣ 입력설명
    첫 줄에 자연수 N이 주어지고, 그 다음 줄에 N개의 정수가 입력된다.
▣ 출력설명
    자신의 바로 앞 수보다 큰 수만 한 줄로 출력한다.
▣ 입력예제
    6
    7 3 9 5 6 12
▣ 출력예제
    7 9 6 12
* */
public class Problem01 {
    public static void solution01(int[] numbers) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > numbers[i - 1]) {
                list.add(numbers[i]);
            }
        }

        System.out.println(list);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int totalAmount = input.nextInt();
        int numbers[] = new int[totalAmount];
        for (int i = 0; i < totalAmount; i++) {
            numbers[i] = input.nextInt();
        }
        solution01(numbers);
    }
}
