package algorithum.RecursionGraph;

import java.util.Scanner;

// 하나의 스택 프레임에는 매개변수 정보, 지역 변수 정보, 복귀 주소 정보
public class Problem01 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        printRecursive(n);
    }

    public static void printRecursive(int n) {
        if (n == 0) {
            return;
        }

        printRecursive(n - 1);
        System.out.print(n + " ");
    }
}
