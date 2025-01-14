package algorithum.RecursionGraph02;

import java.util.*;

public class MakeAllCombinationsOfChars {

    static ArrayList<Set<String>> answer;
    static boolean[] isIncluded;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        isIncluded = new boolean[string.length()];

        answer = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            answer.add(new HashSet<>());
        }

        dfsVersion01(0, string, new String());

        for (int i = 0; i < answer.size(); i++) {
            String[] value = answer.get(i).toArray(String[]::new);
            Arrays.sort(value);
            System.out.println(Arrays.toString(value));
        }

        System.out.println("========= version02 ==========");
        answer = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            answer.add(new HashSet<>());
        }
        dfsVersion02(string);
        for (int i = 0; i < answer.size(); i++) {
            String[] value = answer.get(i).toArray(String[]::new);
            Arrays.sort(value);
            System.out.println(Arrays.toString(value));
        }
    }

    public static void dfsVersion01(int nth, String string, String value) {
        if (nth == string.length()) {
            if (value.length() > 0) {
                answer.get(value.length() - 1).add(value);
            }
            return;
        }


        for (int i = 0; i < string.length(); i++) {
            if (!isIncluded[i]) {
                isIncluded[i] = true;
                dfsVersion01(nth + 1, string, new String(value + string.charAt(i)));
                isIncluded[i] = false;
                dfsVersion01(nth + 1, string, new String(value));
            }
        }
    }

    /*
     * 아래 코드는 재귀 기반 dfs가 아닌, 반복문 기반 dfs로 내가 직접 stack을 생성해서 모든 경우의 수를 찾는 방법이다.
     * 재귀 호출은 자기 자신을 호출하기에 앞서서 방문 여부를 표시된 후 진행하기 때문에, 해당 방문 상태가 유지된다.
     * 스택 프레임 관점으로 이야기하자면 해당 방문 값을 수정된 후, 재귀호출을 하기 때문에 이후 모든 재귀 호출은 해당 값이 각 스택마다 유지된다.
     * 하지만 반복문을 사용하면 각 방문 경우마다 별도로 관리해야 하는 번거로움 존재한다. 그래서 결국 Node 를 생성해서 관리하는 방식으로 흘러간다.
     * */
    public static void dfsVersion02(String string) {
        Deque<Node> stack = new ArrayDeque<>();
        stack.add(new Node("", new boolean[string.length()]));

        while (!stack.isEmpty()) {
            Node current = stack.pollLast();
            String currentString = current.value;
            boolean[] currentIsVisited = current.isVisited;

            if (currentString.length() > 0) {
                answer.get(currentString.length() - 1).add(currentString);
            }

            for (int i = 0; i < string.length(); i++) {
                if (!currentIsVisited[i]) {
                    boolean[] newIsVisited = Arrays.copyOf(currentIsVisited, currentIsVisited.length); // 상태 계속 업데이트
                    newIsVisited[i] = true;
                    stack.add(new Node(currentString + string.charAt(i), newIsVisited));
                }
            }
        }
    }

    public static class Node {
        String value;
        boolean[] isVisited;

        Node(String value, boolean[] isVisited) {
            this.value = value;
            this.isVisited = isVisited;
        }
    }
}