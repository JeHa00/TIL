package algorithum.array;

import java.util.Scanner;

public class Problem03 {
    public static void solution01(int[] A, int[] B) {
        for (int i = 0;  i < A.length; i++) {
            System.out.println(decideWinner(A[i], B[i]));
        }
    }

    public static String decideWinner(int A, int B) {
        if (A == B) {
            return "D";
        } else if (A == 1 && B == 3) {
            return "A";
        } else if (A == 2 && B == 1) {
            return "A";
        } else if (A == 3 && B == 2) {
            return "A";
        } else {
            return "B";
        }
//
//        if (A == 1) {
//            if (B == 1) {
//                return "D";
//            } else if (B == 2) {
//                return "B";
//            } else {
//                return "A";
//            }
//        } else if (A == 2) {
//            if (B == 1) {
//                return "A";
//            } else if (B == 2) {
//                return "D";
//            } else {
//                return "B";
//            }
//        } else { // A == 3
//            if (B == 1) {
//                return "B";
//            } else if (B == 2) {
//                return "A";
//            } else {
//                return "D";
//            }
//        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int chance = input.nextInt();
        int A[] = new int[chance];
        int B[] = new int[chance];
        for (int i = 0;  i < chance; i++) {
            A[i] = input.nextInt();
        }
        for (int i = 0;  i < chance; i++) {
            B[i] = input.nextInt();
        }

        solution01(A, B);
    }
}
