package algorithum.array;

import java.util.*;

public class Problem12 {
    public static int solution01(int[][] scores) {
        int count = 0;

        Map<Integer, Set<Integer>> mentoringPair = new HashMap<>();

        for (int testId = 0; testId < scores.length; testId++) {
            int[] test = scores[testId];
            for (int i = 0; i < test.length; i++) {
                Integer key = test[i];
                if (!mentoringPair.containsKey(key)) {
                    mentoringPair.put(key, new HashSet<>());
                }

                for (int j = i + 1; j < test.length; j++) {
                    mentoringPair.get(key).add(test[j]);
                }
            }
        }
        for (Integer integer : mentoringPair.keySet()) {
            Set<Integer> value = mentoringPair.get(integer);
            Integer[] valuesAsInteger = value.toArray(new Integer[value.size()]);

            for (Integer v : valuesAsInteger) {
                Set<Integer> otherValue = mentoringPair.get(v);
                if (otherValue.contains(integer)) {
                    otherValue.remove(integer);
                    value.remove(v);
                }
            }
        }

        for (Set<Integer> value : mentoringPair.values()) {
            count += value.size();
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int students = input.nextInt();
        int tests = input.nextInt();
        int scores[][] = new int[tests][students];

        for (int testId = 0; testId < tests; testId++) {
            for (int nth = 0; nth < students; nth++) {
                scores[testId][nth] = input.nextInt();
            }
        }

        System.out.println(solution01(scores));
    }
}
