import java.util.Scanner;

public class Variable {
    public static void main(String[] args) {
        // 변수 학습

        boolean myBoolean = true;

        myBoolean = false;

        System.out.println(myBoolean);

        int myInt = 123;

        // myInt = 12345678910; 에러 발생 integer number too large

        long myLong = 12345678910L;

        System.out.println(myLong);

        System.out.println("숫자 연산자");
        int a = 9;
        int b = 5;
        System.out.println(a + " + " + b + " = " + (a + b)); // 14
        System.out.println(a + " - " + b + " = " + (a - b)); // 4
        System.out.println(a + " * " + b + " = " + (a * b)); // 45
        System.out.println(a + " / " + b + " = " + (a / b)); // 1
        System.out.println(a + " % " + b + " = " + (a % b)); // 4

        double c = 5;
        System.out.println(a + " + " + c + " = " + (a + c)); // 14
        System.out.println(a + " - " + c + " = " + (a - c)); // 4
        System.out.println(a + " * " + c + " = " + (a * c)); // 45
        System.out.println(a + " / " + c + " = " + (a / c)); // 1
        System.out.println(a + " % " + c + " = " + (a % c)); // 4

        System.out.println();

        System.out.println("비교 연산자");
        System.out.println(3 + " > " + 2 + " : " + (3 > 2));
        System.out.println(3 + " < " + 2 + " : " + (3 < 2));
        System.out.println(3 + " >= " + 2 + " : " + (3 >= 2));
        System.out.println(3 + " <= " + 2 + " : " + (3 <= 2));
        System.out.println(3 + " == " + 2 + " : " + (3 == 2));
        System.out.println(3 + " != " + 2 + " : " + (3 != 2));

        double x = 3.14;
//        int y = x;
        int y = 3;
        double z = y;
        System.out.println(x);
        int w = (int) x;
        System.out.println(w);

        System.out.println();

        System.out.println("Scanner 실습");
        System.out.println("나이가 어떻게 되시나요??");

        Scanner scanner = new Scanner(System.in);
        int age = scanner.nextInt();
        System.out.println("나이는: " + age);

        System.out.println("성함이 어떻게 되시나요??");
        String name = scanner.next();
        System.out.println("이름은: " + name);

        System.out.println();

        System.out.println("첫 번째 사람의 이름을 입력하세요 : ");
        String firstName = scanner.next();
        System.out.println("두 번째 사람의 이름을 입력하세요 : ");
        String secondName = scanner.next();

        System.out.println("첫 번째 사람이 산 첫 번째 음식을 입력하세요: ");
        String firstFoodOfFirstPerson = scanner.next();
        System.out.println("첫 번째 사람이 산 두 번째 음식을 입력하세요: ");
        String secondFoodOfFirstPerson = scanner.next();
        System.out.println("첫 번째 사람이 산 세 번째 음식을 입력하세요: ");
        String thirdFoodOfFirstPerson = scanner.next();
        System.out.println("두 번째 사람이 산 첫 번째 음식을 입력하세요: ");
        String firstFoodOfSecondPerson = scanner.next();
        System.out.println("두 번째 사람이 산 두 번째 음식을 입력하세요: ");
        String secondFoodOfSecondPerson = scanner.next();
        System.out.println("두 번째 사람이 산 세 번째 음식을 입력하세요: ");
        String thirdFoodOfSecondPerson = scanner.next();

        int firstFoodPriceOfFirstPerson = 2000;
        int secondFoodPriceOfFirstPerson = 800;
        int thirdFoodPriceOfFirstPerson = 1200;

        int firstFoodPriceOfSecondPerson = 6200;
        int secondFoodPriceOfSecondPerson = 2000;
        int thirdFoodPriceOfSecondPerson = 3200;

        String string1 = "은 ";
        String string2 = "는 ";
        String string3 = "을 ";
        String string4 = "를 ";
        String won = "원";

        System.out.println(firstName + "와 " + secondName + string2 + "함께 음식을 준비했습니다.");
        System.out.println(firstName + string2 + firstFoodOfFirstPerson + ", " + secondFoodOfFirstPerson + ", " + thirdFoodOfFirstPerson + string4 + "샀습니다.");
        System.out.println(secondName + string2 + firstFoodOfSecondPerson + ", " + secondFoodOfSecondPerson + ", " + thirdFoodOfSecondPerson + string4 + "샀습니다.");
        System.out.println(firstFoodOfFirstPerson + string2 +  firstFoodPriceOfFirstPerson + won + ", "+ secondFoodOfFirstPerson + string2 + secondFoodPriceOfFirstPerson + won + ", " + thirdFoodOfFirstPerson +string2 + thirdFoodPriceOfFirstPerson + won + "입니다.");
        System.out.println(firstFoodOfSecondPerson + string1 +  firstFoodPriceOfSecondPerson + won + ", "+ secondFoodOfSecondPerson + string1 + secondFoodPriceOfSecondPerson + won + ", " + thirdFoodOfSecondPerson +string2 + thirdFoodPriceOfSecondPerson + won + "입니다.");

        int totalPriceOfFirstPerson = firstFoodPriceOfFirstPerson + secondFoodPriceOfFirstPerson + thirdFoodPriceOfFirstPerson;
        int totalPriceOfSecondPerson = firstFoodPriceOfSecondPerson + secondFoodPriceOfSecondPerson + thirdFoodPriceOfSecondPerson;

        int totalPrice = totalPriceOfFirstPerson +  totalPriceOfSecondPerson;

        int differencePrice = totalPriceOfSecondPerson - totalPriceOfFirstPerson;

        System.out.println("총 합" + string1 + totalPrice + won +"이 나왔습니다.");
        System.out.println(firstName + string2 + totalPriceOfFirstPerson + won + string3 +"지불하고");
        System.out.println(secondName +string2 + totalPriceOfSecondPerson + won + string3 + "지불하였습니다.");
        System.out.println(secondName + string2 + firstName + "보다 " + differencePrice + won + string3 + "더 지불했습니다.");


    }
}
