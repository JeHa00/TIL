package algorithum.HashMapSetTreeSet;

import java.util.*;

public class Problem05 {
    public static int solution01(int[] values, int k) {
        Integer[] array = Arrays.stream(values).boxed().toArray(Integer[]::new);
        Arrays.sort(array, Comparator.reverseOrder());
        Set<Integer> set = new LinkedHashSet<>();
        for (Integer value : array) {
            set.add(value);
            if (set.size() == 3) {
                return value.intValue();
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        int sales[] = new int[n];
        for (int i = 0; i < n; i++) {
            sales[i] = input.nextInt();
        }
        System.out.println(solution01(sales, k));
    }
}
