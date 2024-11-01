package algorithum.HashMapSetTreeSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem02 {
    public static String solution01(String word01, String word02) {
        Map<Character, Integer> map01 = new HashMap<>();
        for (char c : word01.toCharArray()) {
            map01.put(c, map01.getOrDefault(c, 0) + 1);
        }

        String result = "YES";
        for (char c : word02.toCharArray()) {
            if (!map01.containsKey(c) || map01.get(c) == 0) {
                return "NO";
            }
            map01.put(c, map01.get(c) - 1);

        }

        return result;

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String word01 = input.next();
        String word02 = input.next();
        System.out.println(solution01(word01, word02));
    }
}
