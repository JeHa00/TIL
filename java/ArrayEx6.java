import java.util.Scanner;

public class ArrayEx6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("입력받을 숫자의 개수를 입력하세요: ");
        int totalAmountOfNumber = input.nextInt();

        int[] array = new int[totalAmountOfNumber];

        System.out.println(totalAmountOfNumber + "개의 정수를 입력하세요: ");

        int minValue = 1000000000, maxValue = 0;

        for (int i = 0; i < totalAmountOfNumber; i++) {
            int intValue = input.nextInt();

            array[i] = intValue;

            if (intValue < minValue) {
                minValue = intValue;
            } else if (intValue > maxValue) {
                maxValue = intValue;
            }
        }

        System.out.println("가장 작은 정수: " + minValue);
        System.out.println("가장 큰 정수: " + maxValue);

    }
}

