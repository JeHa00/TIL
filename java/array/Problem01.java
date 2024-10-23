package array;

import java.util.LinkedList;
import java.util.Scanner;

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
