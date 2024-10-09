import java.util.Scanner;

public class MethodEx4 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int balance = 0;

        while (true) {
            int option = chooseOption();

            switch (option) {
                case 1:
                    System.out.print("입금액을 입력하세요: ");
                    int moneyToDoDeposit = input.nextInt();
                    balance = deposit(balance, moneyToDoDeposit);
                case 2:
                    System.out.print("출금액을 입력하세요: ");
                    int moneyToBeWithdrawn = input.nextInt();
                    balance = withdraw(balance, moneyToBeWithdrawn);
                case 3:
                    checkCurrentBalance(balance);
                case 4:
                    exit();
//                  break;  // break를 사용하면 switch만 끝나고 while문까지 끝나지 않는다.
                default:
                    System.out.println("위 옵션에 해당되는 번호를 입력하세요.");
            }
        }
    }

    public static int chooseOption() {
        Scanner input = new Scanner(System.in);

        System.out.println("------------------------------------");
        System.out.println("1. 입금 | 2. 출금 | 3. 잔액 확인 | 4. 종료");
        System.out.println("------------------------------------");

        System.out.print("선택: ");
        return input.nextInt();
    }

    public static int deposit(int balance, int money) {
        balance += money;
        System.out.println(money + "원을 입금하였습니다. 현재 잔액: " + balance + "원");
        return balance;
    }

    public static int withdraw(int balance, int money) {
        if (balance >= money) {
            balance -= money;
            System.out.println(money + "원을 출금하였습니다. 현재 잔액: " + balance + "원");
        } else {
            System.out.println(money + "원을 출금하려 했으나 잔액이 부족합니다.");
        }
        return balance;
    }

    public static void checkCurrentBalance(int balance) {
        System.out.println("현재 잔액: " + balance + "원");
    }

    public static void exit() {
        System.out.println("시스템을 종료합니다.");
        return;
    }
}

