
// 캐스팅 형 변환
public class Casting01 {
    public static void main(String[] args) {
    int intValue = 10;
    long longValue;
    double doubleValue;

    longValue = intValue; // int -> long
    System.out.println("longValue = " + longValue);

    doubleValue = intValue; // int -> double (실수)
    System.out.println("doubleValue = " + doubleValue);

    doubleValue = (double) intValue; // int -> double (실수)
    System.out.println("doubleValue = " + doubleValue);

    doubleValue = 20L;
    System.out.println("dobuleValue = " + doubleValue);

    }
}

