public class Overloading1 {
    public static void main(String[] args) {
        System.out.println("1 : " + add(1, 2));
        System.out.println("2 : " + add(1, 2, 3));
        myMethod(1, 1.2);
        myMethod(1.2, 2);
    }

//    public static int add(int a, int b) {
//        System.out.println("1번 호출");
//        return a + b;
//    }

    public static double add(double a, double b) {
        System.out.println("3번 호출");
        return a + b;
    }

// 메서드 시그니처에는 반환값이 포함되지 않는다. 그래서 반환 타입으로 메서드를 구분하지 않는다. 메서드 시그니처에는 메서드 이름과 매개변수 순서, 타입이 포함된다.  
//    public static int add(double a, double  b) {
//        System.out.println("3.5번 호출");
//        return (int) a + b;
//    }

    public static int add(int a, int b, int c) {
        System.out.println("2번 호출");
        return a + b + c;
    }

    public static void myMethod(int a, double b) {

        System.out.println("int a, double b");
    }

    public static void myMethod(double a, int b) {

        System.out.println("double a, int b");
    }
}

