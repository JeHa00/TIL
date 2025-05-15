import java.util.Scanner;

public class BubbleSortingExercise06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }
        int T = sc.nextInt();

        bubbleSorting(numbers);

        findPair(numbers, T);
    }

    public static void bubbleSorting(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
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

    public static void findPair(int[] numbers, int T) {
        int lp = 0;
        int rp = numbers.length - 1;

        while (lp < rp) {
            int sum = numbers[lp] + numbers[rp];

            if (sum < T) {
                lp++;
            } else if (sum > T) {
                rp--;
            } else {
                System.out.println(numbers[lp] + " " + numbers[rp]);
                return;
            }
        }

        System.out.println("NO PAIR");
    }
}
