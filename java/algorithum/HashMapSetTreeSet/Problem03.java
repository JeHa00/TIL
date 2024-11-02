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

    // O(n)
    public static void solution02(int[] sales, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int lp = 0;
        for (int i = 0; i < sales.length; i++) {
            map.put(sales[i], map.getOrDefault(sales[i], 0) + 1);

            if ((i - lp) == k - 1) {
                System.out.print(map.keySet().size() + " ");
                map.put(sales[lp], map.get(sales[lp]) - 1);
                if (map.get(sales[lp]) == 0) {
                    map.remove(sales[lp]);
                }
                lp++;
            }
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
        System.out.println();
        solution02(sales, k);
    }
}
