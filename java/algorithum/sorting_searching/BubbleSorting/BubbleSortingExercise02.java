import java.util.Scanner;

/**
 * 1. 변수명을 보다 명확히 하자. (v)
 * 2. 횟수 계산을 일관되게 하기 (v)
 * 3. swapped 메서드를 만들어서 처리하기 (v)
 * */

/**
 * 아래 코드는 버블 정렬에서 정렬 완료 시 조기 종료를 해서 최적화하기 위함이다.
 * */

public class BubbleSortingExercise02 {
    static int passes;
    static int[] array;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = input.nextInt();
        }

        if (array.length > 1) {
            bubbleSorting(array);
        }

        showArray(array);
        System.out.println("Passes: " + passes);
    }

    public static void bubbleSorting(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            passes = i + 1;

            if (checkToSwap(swapped)) {
                return;
            }
        }
    }

    public static void showArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static boolean checkToSwap(boolean swapped) {
        return (!swapped) ? true : false;
    }
}
