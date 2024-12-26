package algorithum.RecursionGraph;

import java.util.Scanner;

public class Problem06 {

    static int N;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        boolean isIncluded[] = new boolean[N + 1];
        findSubSet(1, isIncluded);
    }

    public static void findSubSet(int element, boolean[] isIncluded) {
        if (element == N + 1) {
            System.out.println(makeSubSet(isIncluded));
            return;
        }

        isIncluded[element] = true;
        findSubSet(element + 1, isIncluded);

        isIncluded[element] = false;
        findSubSet(element + 1, isIncluded);


    }

    public static String makeSubSet(boolean[] isIncluded) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < isIncluded.length; i++) {
            if (isIncluded[i]) {
                sb.append((i + " "));
            }
        }
        return sb.toString();
    }
}
