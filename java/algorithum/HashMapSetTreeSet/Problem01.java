package algorithum.HashMapSetTreeSet;

import java.util.*;

public class Problem01{
    public static char  solution01(String votes) {
        Map<Character, Integer> map = new HashMap<>();

        for (char vote : votes.toCharArray()) {
            map.put(vote, map.getOrDefault(vote, 0) + 1);
        }

        char vote = ' ';
        int max = Integer.MIN_VALUE;
        for (char key : map.keySet()) {
            Integer value = map.get(key);
            if (max < value.intValue()) {
                vote = key;
                max = value;
            }
        }
        return vote;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int students = input.nextInt();
        String votes = input.next();
        System.out.println(solution01(votes));
    }

}
