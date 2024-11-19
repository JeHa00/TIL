package algorithum.sorting_searching;

import java.util.Arrays;
import java.util.Scanner;

// 선택 정렬은 지정한 범위에서 제일 작은 값을 찾아서 현재 범위에서 제일 앞에 두고, 범위를 점차 줄이면서 반복하는 정렬 방식

public class SelectionSorting {
    public static int[] solution01(int[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            int index = 0;
            for (int j = i + 1; j < values.length; j++) {
                if (values[j] < values[index]) index = j;
            }

            int temp = values[i];
            values[i] = values[index];
            values[index] = temp;
        }
        return values;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int values[] = new int[n];
        for (int i = 0; i < values.length; i++) {
            values[i] = input.nextInt();
        }
        for (int value: solution01(values)) {
            System.out.print(value + " ");
        }
    }
}
