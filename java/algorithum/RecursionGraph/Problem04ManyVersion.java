package algorithum.RecursionGraph;

import java.util.Scanner;

// 재귀가 반복문보다 성능이 안좋다. 재귀는 스택 프레임이 계속 생성되기 때문에 메모리 낭비가 심해 무겁다. 하지만 반복문은 스택 프레임이 하나만 생긴다.
// 면접에서 많이 들어오는 질문이다.
// 만약 항의 수가 증가하면 하나 하나를 계산하며 출력하는 시간이 많이 걸린다. 처음부터 다 계산해야 하기 때문이다.
// 어떻게 해야 이 속도가 빨라질까? 메모라이제이션을 사용하자.


public class Problem04ManyVersion {

    static int[] fibonacci;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        fibonacciOfArray(N);
        System.out.println();

        fibonacci = new int[N + 1];
        fibonacciOfRecursion(N);
        for (int i = 1; i < fibonacci.length; i++) {
            System.out.print(fibonacci[i] + " ");
        }
    }

    public static void fibonacciOfArray(int N) {
        int fibonacci[] = new int[N];
        fibonacci[0] = 1;
        fibonacci[1] = 1;

        for (int i = 2; i < N; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }

        for (int value : fibonacci) {
            System.out.print(value + " ");
        }
    }

    public static int fibonacciOfRecursion(int N) {
        if (fibonacci[N] > 0) {
            return fibonacci[N];
        }

        if (N == 1 || N == 2) {
            fibonacci[N] = 1;
            return fibonacci[N];
        }

        fibonacci[N] = fibonacciOfRecursion(N - 1) + fibonacciOfRecursion(N - 2);
        return fibonacci[N];
    }
}
