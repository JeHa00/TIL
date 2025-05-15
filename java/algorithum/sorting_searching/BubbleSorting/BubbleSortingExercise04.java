import java.util.*;
import java.util.stream.Collectors;

public class BubbleSortingExercise04 {
    static List<Integer> odd = new ArrayList<>();
    static List<Integer> even = new ArrayList<>();

    public static void main(String[] args) {
        sort(init());
        showArray();
    }

    public static boolean init() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            int number = sc.nextInt();
            numbers[i] = number;
            if ((i + 1) % 2 == 0) {
                even.add(number);
            } else {
                odd.add(number);
            }
        }

        String howToSort = sc.next();
        return howToSort.equalsIgnoreCase("EVEN");
    }

    public static void sort(boolean isEvenSort) {
        int[] array;
        if (isEvenSort) {
            array = even.stream().mapToInt(Integer::intValue).toArray();
            bubbleSorting(array);
            even = Arrays.stream(array).boxed().collect(Collectors.toList());
        } else {
            array = odd.stream().mapToInt(Integer::intValue).toArray();
            bubbleSorting(array);
            odd = Arrays.stream(array).boxed().collect(Collectors.toList());
        }
    }

    public static void bubbleSorting(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                    swapped = true;
                }
            }

            if (!swapped) {
                return;
            }
        }
    }

    public static void showArray() {
        int i = 0;
        int j = 0;
        while (true) {
            if (odd.size() != i) {
                System.out.print(odd.get(i) + " ");
                i++;
            }

            if (even.size() != j) {
                System.out.print(even.get(j) + " ");
                j++;
            }

            if (odd.size() == i && even.size() == j) {
                break;
            }
        }
    }
}
