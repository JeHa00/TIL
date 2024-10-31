package algorithum.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * two pointers 와 sliding window 는 O(n2)인 것을 O(n)으로 푸는 알고리즘이다.
 * */

public class Problem01 { //  두 배열 합치기
    // 합쳐서 정렬해서 출력하는 방법 -> 퀵정렬로 O(nlog n)
    public static void solution01(int[] numbers01, int[] numbers02) {
        int[] answer = Arrays.copyOf(numbers01, numbers01.length + numbers02.length);
        for (int i = numbers01.length; i < numbers01.length + numbers02.length; i++) {
            answer[i] = numbers02[i - numbers01.length];
        }
        Arrays.sort(answer);
        System.out.println(Arrays.toString(answer));
    }

    // two pointers 방식
    public static void solution02(int[] number01, int[] number02) {
        ArrayList list = new ArrayList();

        int p1 = 0, p2 = 0;

        while (p1 < number01.length && p2 < number02.length) {
            if (number01[p1] < number02[p2]) {
                list.add(number01[p1]);
                p1++;
            } else {
                list.add(number02[p2]);
                p2++;
            }
        }

        while (p1 < number01.length) {
            list.add(number01[p1]);
        }

        while (p2 < number02.length) {
            list.add(number02[p2]);
        }

        System.out.println(list);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int numbers01[] = new int[size];
        for (int i = 0; i < size; i++) {
            numbers01[i] = input.nextInt();
        }

        size = input.nextInt();
        int numbers02[] = new int[size];
        for (int i = 0; i < size; i++) {
            numbers02[i] = input.nextInt();
        }
        solution01(numbers01, numbers02);
    }
}
