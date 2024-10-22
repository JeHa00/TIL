package array;

import java.util.Scanner;

public class CountBiggerThanFirst {
    public static int solution01(int[] list) {
        int count = 1;
        int max = list[0];

        for (int i = 1; i < list.length; i++) {
            if (list[i] > max) {
                count++;
                max = list[i];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int totalAmount = input.nextInt();
        int tallList[] = new int[totalAmount];
        for (int i = 0; i < totalAmount; i++) {
            tallList[i] = input.nextInt();
        }
        System.out.print(solution01(tallList));
    }
}
