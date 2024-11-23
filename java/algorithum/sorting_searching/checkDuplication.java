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


    // HashMap으로 해도 되지만, 이를 정렬로도 풀 수 있는 것을 이야기하기 위함이다.
    // HashMap으로 하면 시간복잡도가 O(n)이다.
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
