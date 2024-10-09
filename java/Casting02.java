public class Casting02 {
    public static void main(String[] args) {
        // 작은 범위의 형식 -> 큰 범위의 다른 형식으로 casting: 명시적 형변환
        double doubleValue = 1.5;
        int intValue = 0;

        // intValue = doubleValue;

        intValue = (int) doubleValue;
        System.out.println(intValue);

    }
}

