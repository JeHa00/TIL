package two_pointers;

import java.util.Arrays;
import java.util.Scanner;

public class Problem01 {
    public static void solution01(int[] numbers01, int[] numbers02) {
        int[] answer = Arrays.copyOf(numbers01, numbers01.length + numbers02.length);
        for (int i = numbers01.length; i < numbers01.length + numbers02.length; i++) {
            answer[i] = numbers02[i - numbers01.length];
        }
        Arrays.sort(answer);
        System.out.println(Arrays.toString(answer));
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
