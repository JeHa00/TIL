package algorithum.two_pointers;

import java.util.Scanner;

public class Problem04 { // 연속 부분수열
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int sales[] = new int[n];
        for (int i = 0; i < n; i++) {
            sales[i] = input.nextInt();
        }

        System.out.println(solution01(sales, m));
        System.out.println(solution02(sales, m));
        System.out.println(solution03(sales, m));
    }

    // O(n2)
    public static int solution01(int[] values, int m) {
        int count = 0;

        for (int i = 0; i < values.length; i++) {
            int sum = 0;
            for (int j = i; j < values.length; j++) {
                sum += values[j];
                if (sum > m) {
                    break;
                }

                if (sum == m) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    // O(n)
    // 1 1 1 5 이고 m 이 6인 경우
    public static int solution02(int[] values, int m) {
        int sum = 0, count = 0, minIndex = 0;
        for (int i = 0 ; i < values.length; i++) {
            if (sum < m) {
                sum += values[i];
            } else if (sum == m) {
                count++;
            } else {
                sum -= values[minIndex++];
            }
        }

        return count;
    }

    public static int solution03(int[] values, int m) {
        int count = 0, minIndex = 0, sum = 0;

        for (int i = 0; i < values.length; i++) {
            sum += values[i];

            while (sum > m) {
                sum -= values[minIndex++];
            }

            if (sum == m) {
                count++;
            }
        }

        return count;
    }
}