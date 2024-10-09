import java.util.Scanner;

public class ArrayEx2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("5개의 정수를 입력하세요: ");

        int [] scores = new int[5];

        for (int i = 0; i < scores.length; i++) {
            scores[i] = input.nextInt();
        }

        System.out.println("출력");

        for (int i = 0; i < scores.length; i++) {
            String space = (i == scores.length - 1) ? "" : ", ";
            System.out.print(scores[i] + space);
        }

    }
}

