import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        int[] test = {2, 7, 11, 15};
        int target = 9;
        int answers[] = new int[2];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < test.length; i++) {
            if (map.containsKey(target - test[i])) {
                answers[0] = map.get(target - test[i]);
                answers[i] = i;
                break;
            }

            map.put(target - test[i], i);
        }

        System.out.println(Arrays.toString(answers));
    }
}
