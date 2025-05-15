import java.util.Scanner;

public class BubbleSortingExercise03 {
    static int N;
    static int L;
    static int R;
    static int[] array;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        L = input.nextInt();
        R = input.nextInt();
        array = new int[N];

        if (!validateIndex()) {
            System.out.println("인덱스 값이 적절하지 않습니다.");
            return;
        }

        for (int i = 0; i < N; i++) {
            array[i] = input.nextInt();
        }

        if (array.length > 1) {
            bubbleSort();
        }

        showArray();
    }

    public static boolean validateIndex() {
        if (L > N || R > N || L > R) {
            return false;
        }

        return true;
    }

    public static void bubbleSort() {
        for (int i = 0; i < R - L; i++) {
            boolean swapped = false;
            for (int j = L - 1; j < R - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            if (checkToSwap(swapped)) {
                return;
            }
        }
    }

    public static void showArray() {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static boolean checkToSwap(boolean swapped) {
        return (!swapped) ? true : false;
    }
}
