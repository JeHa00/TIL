package algorithum.sorting_searching;

import java.util.Arrays;
import java.util.Scanner;

public class MusicVideo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int runningTimes[] = new int[n];

        for (int i = 0; i < n; i++) {
            runningTimes[i] = input.nextInt();
        }
        solution(m, runningTimes);
    }

    public static void solution(int m, int[] runningTimes) {
        int lp = Arrays.stream(runningTimes).max().getAsInt(); // minimum capacity;
        int rp = Arrays.stream(runningTimes).sum(); // maximum capacity;
        int count = 1, sum = 0, answer = 0;

        while (lp <= rp) {
            int mid = (lp + rp) / 2;

            for (int time : runningTimes) {
                if (sum + time > mid) {
                    count++;
                    sum = time;
                } else {
                    sum += time;
                }
            }

            if (count > m) {
                lp = mid + 1;
            } else {
                answer = mid;
                rp = mid - 1;
            }

            count = 1;
            sum = 0;
        }
        System.out.println(answer);
    }
}
