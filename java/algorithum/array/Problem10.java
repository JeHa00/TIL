package algorithum.array;

import java.util.Scanner;

public class Problem10 {
    public static int solution01(int[][] map) {
        int count = 0;
        for (int row = 1; row < map.length - 1; row++) {
            for (int column = 1; column < map.length - 1; column++) {
                if (map[row][column] > map[row - 1][column]
                    && map[row][column] > map[row + 1] [column]
                    && map[row][column] > map[row][column - 1]
                    && map[row][column] > map[row][column + 1]
                ) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int length = input.nextInt() + 2;
        int matrix[][] = new int[length][length];

        for (int row = 1; row < length - 1; row++) {
            for (int column = 1; column < length - 1; column++) {
                matrix[row][column] = input.nextInt();
            }
        }

        for (int column = 0; column < length; column++) {
            matrix[0][column] = 0;
            matrix[length - 1][column] = 0;
        }

        for (int row = 0; row < length; row++) {
            matrix[row][0] = 0;
            matrix[row][length - 1] = 0;
        }

        System.out.println(solution01(matrix));
    }
}
