import java.util.Scanner;

public class BubbleSortingExercise05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }

        bubbleSorting(numbers, K);
        show(numbers);
    }

    public static void bubbleSorting(int[] numbers, int K) {
        for (int i = 0; i < K; i++) {
            boolean swapped = false;
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int tmp = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = tmp;
                    swapped = true;
                }
            }

            if (!swapped) {
                return;
            }
        }
    }

    public static void show(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }
}
