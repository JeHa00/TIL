package algorithum.RecursionGraph;

import java.util.Arrays;
import java.util.Scanner;

public class Problem08 {
    static int E;
    static int min;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int S = input.nextInt();
        E = input.nextInt();

        int[] movements = new int[3]; // -1, +1, +5
        System.out.println(min);
    }

    public static void bfs() {

    }

    // stackOverFlowError 발생
//    public static int bfs(int currentPosition, int[] movements) {
//        int movementDistance = movements[0] * (-1) + movements[1] * 1 + movements[2] * 5;
//
//        if (movementDistance + currentPosition - E < 5) {
//            int[] plus = Arrays.copyOf(movements, movements.length);
//            plus[1]++;
//            return bfs(currentPosition, plus) + 1;
//        } else if (movementDistance + currentPosition - E >= 5) {
//            int[] jump = Arrays.copyOf(movements, movements.length);
//            jump[2]++;
//            return bfs(currentPosition, jump) + 1;
//        } else if (movementDistance + currentPosition < E) {
//            int[] minus = Arrays.copyOf(movements, movements.length);
//            minus[0]++;
//            return bfs(currentPosition, minus) + 1;
//        } else {
//            return 0;
//        }
//    }

}
