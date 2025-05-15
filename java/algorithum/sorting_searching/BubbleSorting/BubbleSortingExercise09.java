import java.util.Arrays;
import java.util.Scanner;

/*
* 5
  4 1 3 2 5
* */

public class BubbleSortingExercise09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] values = new int[N];
        for (int i = 0; i < N; i++) {
            values[i] = sc.nextInt();
        }

        bubbleSorting(values);
        sortZigZagPattern(values);
    }

    public static void bubbleSorting(int[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < values.length - 1 - i; j++) {
                if (values[j] > values[j + 1]) {
                    int tmp = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = tmp;
                    swapped = true;
                }
            }

            if (!swapped) {
                return;
            }
        }
    }

    public static void sortZigZagPattern(int[] values) {
        for (int i = 1; i < values.length - 1; i++) {
            if ((i + 1) % 2 == 0 && (values[i] < values[i + 1])) {
                int tmp = values[i + 1];
                values[i + 1] = values[i];
                values[i] = tmp;
            }
        }

        System.out.println(Arrays.toString(values));
    }
}
