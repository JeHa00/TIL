import java.util.Arrays;
import java.util.Scanner;

public class BubbleSortingExercise {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        if (arr.length == 0) {
            System.out.println("원소가 없습니다.");
            return;
        }

        if (arr.length == 1) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        optimizedBubbleSorting(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void optimizedBubbleSorting(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                return;
            }
        }
    }
}
