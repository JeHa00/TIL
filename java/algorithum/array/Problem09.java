package array;

import java.util.Arrays;
import java.util.Scanner;

public class Problem09 {
    public static int solution01(int[][] matrix, int[][] reversedMatrix) {
        int sum = 0, sum02 = 0, sum03 = 0;
        for (int row = 0; row < matrix.length; row++) {
            sum = Math.max(sum, Arrays.stream(matrix[row]).sum());
            sum02 += matrix[row][row];
            sum03 += matrix[row][matrix.length - 1 - row];

        }

        sum = Math.max(sum, Math.max(sum02, sum03));


        for (int column = 0; column < reversedMatrix.length; column++) {
            sum = Math.max(sum, Arrays.stream(reversedMatrix[column]).sum());
        }

        return sum;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        int matrix[][] = new int[length][length];
        int reversedMatrix[][] = new int[length][length];

        for (int row = 0; row < length; row++) {
            for (int column = 0; column < length; column++) {
                matrix[row][column] = input.nextInt();
            }
        }


        for (int column = 0;  column < length; column++) {
            for (int row = 0; row < length; row++) {
                reversedMatrix[column][row] = matrix[row][column];
            }
        }

        System.out.println(solution01(matrix, reversedMatrix));
    }
}
