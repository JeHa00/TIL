package algorithum.sorting_searching;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SortCoordinate {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int points[][] = new int[N][N];
        for (int i = 0; i < points.length; i++) {
            points[i][0] = input.nextInt(); // x
            points[i][1] = input.nextInt(); // y
        }

        solution01(points);
    }

    public static void solution01(int[][] points) {

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0]) {
                    return -1;
                } else if (o1[0] > o2[0]) {
                    return 1;
                } else {
                    if (o1[1] < o2[1]) {
                        return -1;
                    } else if (o1[1] > o2[1]) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        });

        for (int i = 0; i < points.length; i++) {
            System.out.println(points[i][0] + " " + points[i][1]);
        }
    }
}
