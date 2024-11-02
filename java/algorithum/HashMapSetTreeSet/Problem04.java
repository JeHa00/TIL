package algorithum.HashMapSetTreeSet;

import java.util.*;

public class Problem04 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String value01 = input.next();
        String value02 = input.next();
        System.out.println(solution01(value01, value02));
    }


    public static int solution01(String s, String anagram) {
        HashMap<Character, Integer> answer = new HashMap<>();
        for (int i = 0; i < anagram.length(); i++) {
            answer.put(anagram.charAt(i), answer.getOrDefault(anagram.charAt(i), 0) + 1);
        }

        int count = 0, lp = 0;
        HashMap<Character, Integer> choices = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            choices.put(s.charAt(i), choices.getOrDefault(s.charAt(i), 0) + 1);

            if ((i - lp) == anagram.length() - 1) {

                if (answer.equals(choices)) {
                    count++;
                }

                choices.put(s.charAt(lp), choices.get(s.charAt(lp)) - 1);
                if (choices.get(s.charAt(lp)) == 0) {
                    choices.remove(s.charAt(lp));
                }
                lp++;

            }
        }

        return count;

    }
}
