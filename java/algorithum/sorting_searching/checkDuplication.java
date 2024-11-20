package algorithum.sorting_searching;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class checkDuplication {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int values[] = new int[n];
        for (int i = 0; i < values.length; i++) {
            values[i] = input.nextInt();
        }

        System.out.println(solution01(values));
    }

    public static String solution01(int[] values) {
        Map<Integer, Integer> map = new HashMap();

        String answer = "U";

        for (int value : values) {
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
                answer = "D";
            } else {
                map.put(value, 1);
            }
        }

        return answer;
    }
}
