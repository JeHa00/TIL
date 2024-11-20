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

    public static void solution(int[] tall) {
        int chulSoo = -1;
        int pair = -1;

        int[] copied = Arrays.copyOf(tall, tall.length);
        Arrays.sort(tall);

        for (int i = 0; i < tall.length; i++) {
            if (tall[i] != copied[i]) {
                if (chulSoo == -1) {
                    chulSoo = i;
                } else {
                    if (copied[chulSoo] > copied[i]) {
                        pair = i;
                    } else{
                        pair = chulSoo;
                        chulSoo = i;
                    }
                }
            }
        }
        System.out.println((chulSoo + 1) + " " + (pair + 1));
    }
}
