public class MethodReturn1 {
    public static void main(String[] args) {
        boolean result = odd(2);
        System.out.println(result);
        int num1 = 5;
        System.out.println("1. changeNumber 호출 전, num1: " + num1);
        changeNumber(num1);
        System.out.println("4. changeNumber 호출 후, num1: " + num1);
    }

    public static boolean odd(int i) {
        if (i % 2 == 1) {
            return true;
        } else {
            return false;
        }
    }
    public static void checkAge(int age) {
            if (age < 18) {
                System.out.println(age + " 살, 미성년자는 출입이 불가능합니다. ");
                return;
            }
        System.out.println(age + "살, 입장하세요.");
        }

    public static void changeNumber(int num2) {
        System.out.println("2. changeNumber 변경 전, num2: " + num2);
        num2 *= 2;
        System.out.println("3. changeNumber 변경 후, num2: " + num2);
    }

    }
