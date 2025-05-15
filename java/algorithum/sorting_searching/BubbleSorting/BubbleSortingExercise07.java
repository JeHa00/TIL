import java.util.Arrays;
import java.util.Scanner;

public class BubbleSortingExercise07 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = input.nextInt();
        }
        int X = input.nextInt();

        bubbleSorting(array);
        System.out.println(Arrays.toString(array));

        int index = find(array, X);
        System.out.println(index);
    }

    public static void bubbleSorting(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < array.length -1 - i; j++ ) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    swapped = true;
                }
            }

            if (!swapped) {
                return;
            }
        }
    }


    public static int find(int[] array, int X) {
        int lp = 0;
        int rp = array.length - 1;
        while (lp <= rp) {
            int mid = (lp + rp) / 2;

            if (X > array[mid]) {
                lp = mid + 1;
            } else if (X < array[mid]) {
                rp = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
