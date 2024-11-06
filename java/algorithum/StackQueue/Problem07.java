package algorithum.StackQueue;

import java.util.Scanner;

public class Problem07 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String essentialCur = input.next();
        String curriculum = input.next();
        System.out.println(solution01(essentialCur, curriculum));
    }

    public static String solution01(String essentialCur, String curriculum) {
        int j = 0;
        for (int i = 0; i < curriculum.length(); i++) {
            if (j < essentialCur.length() - 1 && curriculum.charAt(i) == essentialCur.charAt(j)) {
                j++;
            }
        }

        return (essentialCur.length() - 1 == j) ? "YES" : "NO";
    }


}
