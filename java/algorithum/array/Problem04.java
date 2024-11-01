package algorithum.array;

import java.util.Scanner;

public class Problem04 {
    public static void solution01(int amount) {
        int array[] = new int[amount];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i < amount; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }

        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void solution02WithoutArray(int amount) {
        int a = 1, b = 1, c;

        System.out.print(a + " " + b + " ");
        for (int i = 2; i < amount; i++) {
            c = a + b;
            System.out.print(c + " ");
            a = b;
            b = c;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int amount = input.nextInt();
        solution01(amount);
        solution02WithoutArray(amount);
    }
}
