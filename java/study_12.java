public class Casting3 {
    public static void main(String[] args) {
        long maxIntValue = 2147483647;
        long maxIntOver = 2147483648L; // int로 표현할 수 있는 수 범위를 넘었기 때문에 'L'을 마지막에 입력해야 한다.
        int intValue = 0;

        System.out.println(maxIntValue);
        intValue = (int) maxIntValue;
        System.out.println(intValue);
        System.out.println(maxIntValue);

        intValue = (int) maxIntOver;
        System.out.println(intValue); // -2147483648
        // int로 표현할 수 있는 범위를 넘었기 때문에 전혀 다른 숫자가 표현되는데, 이를 '오버플로우' 라 한다.
        // 이 경우 int -> long으로 변경해서 사이즈를 늘리면 해결된다.

    }

}

