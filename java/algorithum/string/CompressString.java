package string;

import java.util.*;

public class CompressString {
    public static String solution01(String string) {
        Map<String, Integer> map = new LinkedHashMap<>();

        for (char c : string.toCharArray()) {
            String s = String.valueOf(c);
            if (map.containsKey(s)) {
                Integer value = map.get(s);
                map.put(s, ++value);
            } else {
                map.put(s, 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String s : map.keySet()) {
            sb.append(s);

            Integer value = map.get(s);
            if (value > 1) {
                sb.append(value.intValue());
            }
        }

        return sb.toString();
    }

    public static String solution02(String string) {
        StringBuilder sb = new StringBuilder();
        char prev = string.charAt(0);
        int count = 0;

        string += " ";

        for (char c : string.toCharArray()) {
            if (prev == c) {
                count++;
            } else {
                sb.append(prev);
                if (count > 1) {
                    sb.append(count);
                }
                prev = c;
                count = 1;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        System.out.println(solution01(string));
        System.out.println(solution02(string));
    }
}
