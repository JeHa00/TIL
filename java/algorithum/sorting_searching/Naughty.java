package algorithum.sorting_searching;

import java.util.Arrays;
import java.util.Scanner;

public class Naughty {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int tall[] = new int[N];

        for (int i = 0; i < N; i++) {
            tall[i] = input.nextInt();
        }

        solution(tall);
    }

    // 정렬을 하면 쉽게 풀리는 문제가 있다는 걸 알려주기 위해 추가함
    public static void solution(int[] tall) {
        int value01 = -1;
        int value02 = -1;

        int[] copied = Arrays.copyOf(tall, tall.length);
        Arrays.sort(tall);

        for (int i = 0; i < tall.length; i++) {
            if (tall[i] != copied[i]) {
                if (value01 == -1) {
                    value01 = i;
                } else {
                    value02 = i;
                }
            }
        }

        if (copied[value01] > copied[value02]) {
            System.out.println((value01 + 1) + " " + (value02 + 1));
        } else{
            System.out.println((value02 + 1) + " " + (value01 + 1));

        }
    }
}
