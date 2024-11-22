package algorithum.sorting_searching;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int target = input.nextInt();

        int points[] = new int[N];

        for (int i = 0; i < points.length; i++) {
            points[i] = input.nextInt();
        }

        System.out.println(solution01(points, target));
    }

    public static int solution01(int[] values, int target) {
        Arrays.sort(values);
        int lp = 0;
        int rp = values.length - 1;
        int answer = 0;
        while (lp <= rp) {
            int mid = (lp + rp) / 2;
            if (values[mid] > target) {
                rp = mid;
            } else if (values[mid] < target) {
                lp = mid;
            } else {
                answer = mid;
                break;
            }
        }

        return answer + 1;
    }
}
