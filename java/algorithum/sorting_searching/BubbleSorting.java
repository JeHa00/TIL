package algorithum.sorting_searching;

import java.util.Arrays;
import java.util.Scanner;

// 이웃한 두 수를 비교해서 지정된 정렬 기준으로 위치를 바꿔주는 정렬로, 맨 뒤부터 정렬되기 시작한다.

public class BubbleSorting {
    public static void solution01(int[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            for (int j = i + 1; j < values.length - 1 - i; j++) {
                if (values[j] > values[j + 1]) {
                    int temp = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(values));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int values[] = new int[n];

        for (int i = 0; i < values.length; i++) {
            values[i] = input.nextInt();
        }

        solution01(values);
    }
}
