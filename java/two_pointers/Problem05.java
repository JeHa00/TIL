package two_pointers;

import java.util.Scanner;

public class Problem05 { // 연속된 자연수의 합
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println(solution01(n));
        System.out.println(solution02(n));
        System.out.println(solution03(n));
    }

    public static int solution01(int n) {
        int count = 0;
        for (int i = 2; i < n / 2; i++) {
            for (int j = 1; j <= n; j++) {
                int sum = 0;
                for (int k = j; k < j + i; k++) {
                    sum += k;
                }
                if (sum == n) {
                    count++;
                }
            }
        }

        return count;
    }

    // O(n)
    public static int solution02(int n) {
        int sum = 0, min = 1, count = 0;

        for (int rp = 1; rp < n; rp++) {
            sum += rp;

            while (sum > n) {
                sum -= min++;
            }

            if (sum == n) {
                count++;
            }
        }

        return count;
    }

    /**
     * 수학적 방법으로 푸는 방법
     * n 을 표현하기 위한 자릿수로 예를 들어 두 자리를 찾는다면? 두 자리 이므로 1 + 2 의 합인 3을 15에서 뺀다.
     * 그러면 12가 된다. 12를 2로 나눈다. 6이다. 1과 2에 각각 더한다. 7, 8로 조합을 찾는다.
     * 예를 들어 세 자리를 찾는다면? 세 자리 이므로 1 + 2 + 3 의 합인 6을 15에서 뺀다. 그러면 9가 된다.
     * 9를 3으로 나눈다. 3이다. 1, 2, 3에 각각 3을 더한다. 그러면 4, 5, 6으로 조합을 찾을 수 있다.
     *
     * */
    public static int solution03(int n) {
        int count = 0;

        for (int i = 2; i <= 15; i++) {
            int value = (i + 1) * i / 2; // 6

            if (n < value) {
                break;
            }

            if ((n - value) % i == 0) { //
                count++;
            }

        }

        return count;
    }
}
