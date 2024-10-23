package array;

import java.util.Arrays;
import java.util.Scanner;

public class Problem09 {
    public static int solution01(int[][] matrix01, int[][] matrix02) {
        int sum = 0;
        int sum02 = 0;
        int sum03 = 0;
        for (int row = 0; row < matrix01.length; row++) {
            sum = Math.max(sum, Arrays.stream(matrix01[row]).sum());
            sum02 += matrix01[row][row];
            sum03 += matrix01[row][matrix01.length - 1 - row];

        }

        sum = Math.max(sum, Math.max(sum02, sum03));


        for (int column = 0; column < matrix02.length; column++) {
            sum = Math.max(sum, Arrays.stream(matrix02[column]).sum());
        }

        return sum;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        int matrix01[][] = new int[length][length];
        int matrix02[][] = new int[length][length];

        for (int row = 0; row < length; row++) {
            for (int column = 0; column < length; column++) {
                matrix01[row][column] = input.nextInt();
            }
        }


        for (int column = 0;  column < length; column++) {
            for (int row = 0; row < length; row++) {
                matrix02[column][row] = matrix01[row][column];
            }
        }

        System.out.println(solution01(matrix01, matrix02));
    }
}
