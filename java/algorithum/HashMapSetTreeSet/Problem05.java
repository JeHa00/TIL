package algorithum.HashMapSetTreeSet;

import java.util.*;

public class Problem05 {
    public static int solution01(int[] sales, int k) {
        Set<Integer> set = new TreeSet<>(Comparator.reverseOrder());
        for (int i = 0; i < sales.length; i++) {
            for (int j = i + 1; j < sales.length; j++) {
                for (int n = j + 1; n < sales.length; n++) {
                    set.add(sales[i] + sales[j] + sales[n]);
                }
            }
        }

        int count = 1;
        for (int value :  set) {
            if (count == k) {
                return value;
            }
            count++;
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
