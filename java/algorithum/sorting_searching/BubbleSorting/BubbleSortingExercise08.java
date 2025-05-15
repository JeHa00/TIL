import java.util.Arrays;
import java.util.Scanner;

public class BubbleSortingExercise08 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int M = input.nextInt();
        int[] array01 = makeSortedArray(N);
        int[] array02 = makeSortedArray(M);

        if (N == 0 && M == 0) {
            System.out.println();
            return;
        }

        if (N == 0 && M != 0) {
            System.out.println(Arrays.toString(array02));
        } else if (N != 0 && M == 0) {
            System.out.println(Arrays.toString(array01));
        } else {
            sortTwoArray(array01, array02);
        }
    }

    public static int[] makeSortedArray(int size) {
        Scanner input = new Scanner(System.in);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = input.nextInt();
        }
        bubbleSorting(array);

        return array;
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

    public static void sortTwoArray(int[] first, int[] second) {
        int[] index = new int[2];
        int[] values = new int[2];

        while (true) {
            if (index[0] < first.length) {
                values[0] = first[index[0]];
            } else {
                for (int i = index[1]; i < second.length; i++) {
                    System.out.print(second[i] + " ");
                }
                return;
            }

            if (index[1] < second.length) {
                values[1] = second[index[1]];
            } else {
                for (int i = index[0]; i < first.length; i++) {
                    System.out.print(first[i] + " ");
                }
                return;
            }

            if (values[0] < values[1]) {
                System.out.print(values[0] + " ");
                index[0]++;
            } else if (values[0] > values[1]) {
                System.out.print(values[1] + " ");
                index[1]++;
            } else {
                System.out.print(values[0]);
                index[0]++;
                index[1]++;
            }
        }
    }
}
