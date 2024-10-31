package algorithum.two_pointers;

import java.util.*;

public class Problem02 { // 공통 원소 구하기
    public static void solution01(int[] numbers01, int[] numbers02) {
        Set<Integer> set01 = new HashSet<>();
        Set<Integer> set02 = new HashSet<>();
        for (int i : numbers01) {
            set01.add(i);
        }

        for (int i : numbers02) {
            set02.add(i);
        }

        set02.retainAll(set01);
        Integer[] answer = set02.toArray(new Integer[0]);
        for (Integer integer : answer) {
            System.out.println(integer.intValue() + " ");
        }
    }

    // two pointers
    public static void solution02(int[] numbers01, int[] numbers02) {
        ArrayList<Integer> list = new ArrayList<>();
        int p1 = 0, p2 = 0;

        Arrays.sort(numbers01);
        Arrays.sort(numbers02);

        while (p1 < numbers01.length && p2 < numbers02.length) {
            if (numbers01[p1] == numbers02[p2]) {
                list.add(numbers01[p1]);
                p1++;
                p2++;
            } else if (numbers01[p1] < numbers02[p2]) {
                p1++;
            } else {
                p2++;
            }
        }

        System.out.println(list);

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int size = input.nextInt();
        int numbers01[] = new int[size];
        for (int i = 0; i < numbers01.length; i++) {
            numbers01[i] = input.nextInt();
        }

        size = input.nextInt();
        int numbers02[] = new int[size];
        for (int i = 0; i < numbers01.length; i++) {
            numbers02[i] = input.nextInt();
        }

//        solution01(numbers01, numbers02);
        solution02(numbers01, numbers02);
    }
}
