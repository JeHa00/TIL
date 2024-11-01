package algorithum.HashMapSetTreeSet;

import java.util.*;

public class Problem04 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String value01 = input.next();
        String value02 = input.next();
        System.out.println(solution01(value01, value02));
    }

    public static int solution01(String value01, String value02) {
        Set<String> answer = new HashSet<>();
        for (char c : value02.toCharArray()) {
            answer.add(String.valueOf(c));
        }

        Set<String> checkAnagram = new HashSet<>();
        Deque<String> substring = new ArrayDeque<>();
        int lp = 0, count = 0;

        for (char c : value01.toCharArray()) {
            checkAnagram.add(String.valueOf(c));
            substring.add(String.valueOf(c));

            while (substring.size() > value02.length()) {
                substring.pollFirst();
                lp++;
            }

            if (checkAnagram.size() == value02.length()) {
                boolean result = true;
                for (String value : answer) {
                    if (!checkAnagram.contains(value)) {
                        result = false;
                    }
                }

                checkAnagram.remove(String.valueOf(value01.charAt(lp)));

                if (result) {
                    count++;
                }
            }
        }
        return count;
    }
}
