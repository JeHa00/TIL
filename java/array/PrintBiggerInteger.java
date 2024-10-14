package array;

import java.util.Scanner;

public class PrintBiggerInteger {
    public static void solution01(String string) {

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int totalAmount = input.nextInt();
        int numbers[] = new int[totalAmount];
        for (int i = 0; i < totalAmount; i++) {
            numbers[i] = input.nextInt();
        }
    }
}
