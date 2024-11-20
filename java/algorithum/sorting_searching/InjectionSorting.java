package algorithum.sorting_searching;

import java.util.Arrays;
import java.util.Scanner;

// 특정 값을 원하는 기준대로 정렬하기 위해서 이미 정렬된 부분을 유지하면서 적절한 위치에 삽입하는 방식
// 이 과정에서 값들을 한 방향으로 밀어내는 것이 필요합니다.

public class InjectionSorting {
    public static void solution01(int[] values) {
        for (int i = 1; i < values.length; i++) {
            int target = values[i], j;
            for (j = i - 1; j >= 0; j--) {
                if (values[j] > target) {
                    values[j + 1] = values[j];
                } else break;
            }
            values[j] = target;

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
