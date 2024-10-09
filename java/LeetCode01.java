import java.util.HashMap;
import java.util.Map;

class LeetCode01 {
    public static void main(String args[]) {

    }

    // brute force 방식 O(n2)
    public static int[] solution01(int[] nums, int target) {
        /**
         * j의 값이 i + 1부터 시작하는 이유는 답은 인덱스 중복을 허용하지 않기 때문이다.
         * end 변수를 사용한 이유는 다음과 같다.
         *     - 답을 찾은 즉시 반복을 중단하는 게 자원을 아낄 수 있다.
         *     - [3, 6, 2, 7] 이고 target 9인 경우, 3과 6 조합과 6과 3조합 모두 합하면 target 이 될 수 있는데, 정답은 인덱스가 증가하는 방식으로 원하기 때문이다.
         *     - [3, 3] 이고 target 6인 경우, 중단하지 않으면 인덱스 값이 {1, 0}이 되기 때문이다.
         * */
        int answers[] = new int[2];
        for (int i = 0; i < nums.length; i++) {
            boolean end = false;
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    answers[0] = i;
                    answers[1] = j;
                    end = true;
                }
            }
            if (end) {
                break;
            }
        }

        return answers;
    }

    // HashTable 사용해 O(n)
    public static int[] solution02(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i])) {
                    return new int[]{map.get(nums[i]), i};
                }
                map.put(target - nums[i], i);
            }
            return new int[]{};
        }
    }

}