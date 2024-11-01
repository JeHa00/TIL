package algorithum.HashMapSetTreeSet;

import java.util.*;

public class Problem03 {

    // O(n2)
    public static void solution01(int[] sales, int k) {
        for (int i = 0; i <= sales.length - k; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < i + k; j++) {
                set.add(sales[j]);
            }
            System.out.print(set.size() + " ");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        int sales[] = new int[n];
        for (int i = 0; i < n; i++) {
            sales[i] = input.nextInt();
        }
        solution01(sales, k);
    }
}
